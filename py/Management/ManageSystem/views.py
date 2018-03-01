from django.shortcuts import render, redirect
from .models import Device, User
from django.core.paginator import Paginator, EmptyPage, PageNotAnInteger
import socket
from .exts import *

# Create your views here.

# 主界面（包括展示设备，搜索设备）
def index(request):
    if request.method == 'GET':
        dev_list = Device.objects.all()
        paginator = Paginator(dev_list, 10)
        page = request.GET.get('page', 1)
        currentPage = int(page)
        try:
            dev_list = paginator.page(page)
        except PageNotAnInteger:
            dev_list = paginator.page(1)
        except EmptyPage:
            dev_list = paginator.page(paginator.num_pages)
        return render(request, 'home.html', locals())
    else:
        device_id = request.POST.get('device-id')
        device = Device.objects.filter(device_id=device_id).get()
        return render(request, 'search.html', {'device': device})

# 发送数据界面
def do(request, id):
    if request.method == 'POST':
        device_id = request.POST.get('device-id')
        value1 = request.POST.get('option1')
        value2 = request.POST.get('option2')
        value3 = request.POST.get('option3')
        value4 = request.POST.get('option4')
        if value1 and value2 and value3 and value4 and device_id:
            sk = socket.socket()
            try:
                sk.connect(('127.0.0.1', 12306))
                sk.sendall(hex2Byte('48484A4A0105000000050014' + id + value1 + value2 + value3 + value4 + '4A4A4848'))
            except Exception as e:
                print('0105发送失败' + e)
            finally:
                sk.close()
                return redirect('/')
        else:
            message = '选择错误！'
            return render(request, 'do.html', {'id': id, 'message': message})
    else:
        sk2 = socket.socket()
        try:
            sk2.connect(('127.0.0.1', 12306))
            sk2.sendall(hex2Byte('48484A4A0106000000060010' + id + '4A4A4848'))
            accept_data = sk2.recv(1024)
            strData = byte2Hex(accept_data);
            print(strData)
        except Exception as e:
            print('0106发送失败' + e)
        finally:
            sk2.close()
        return render(request, 'do.html', {'id': id})

# 登陆界面
def login(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')
        role = request.POST.get('option')
        print(username, password, role)
        if username and password and role:
            try:
                user = User.objects.get(username=username)
                if user.password == password and user.role == role:
                    request.session['is_login'] = True
                    request.session['user_id'] = user.id
                    request.session['user_name'] = user.username
                    print('success')
                    return redirect('/')
                else:
                    message = '密码不正确 or 角色错误'
            except:
                message = '用户不存在！'
        return render(request, 'login.html', {'message': message})
    else:
        return render(request, 'login.html')

def logout(request):
    if not request.session.get('is_login', None):
        return redirect('/login/')
    request.session.flush()
    return redirect('/login/')

# 设备录入界面
def record(request):
    return render(request, 'record.html')

# 管理界面
def control(request):
    return render(request, 'control.html')