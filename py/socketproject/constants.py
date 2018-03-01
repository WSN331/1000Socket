import os
import crc16
import math
import time
import pymysql

# 注册包发送数据长度 0101
length_0101 = '000F'
# 心跳包发送数据长度 0102
length_0102 = '0000'
# 重启包发送数据长度 0104
length_0104 = '0000'
# 开关量控制包发送数据长度 0105
length_0105 = '0004'
# 开关量获取包发送数据长度 0106
length_0106 = '0000'
# 主动查询版本包发送数据长度 0501
length_0501 = '0010'
# 主动请求升级包发送数据长度 0502
length_0502 = '0007'
# 被动请求升级包发送数据长度 0503
length_0503 = '0017'
# 尾部
last = '4A4A4848'
# 软件版本
version = '59542D445A59303120302E3032000000'
# 升级文件路径
file_path = '/home/update/dlkzapp222.bin'
# 每个升级包大小
size = 512

# 获取服务器更新文件大小
def getFileSize():
    file_size = os.path.getsize(file_path)
    package_size = math.ceil(file_size / 512)

    return file_size, package_size

# 打开文件并读取数据
def readFile(num, s_num):
    f = open(file_path, 'rb')
    try:
        f.seek(size * num, 0)
        if num < s_num - 1:
            data = f.read(512)
        elif num == s_num - 1:
            data = f.read()
    finally:
        f.close()

# 对数据进行crc16校验
def crc_16(byte_data):
    c = crc16.crc16xmodem(byte_data)
    h = hex(c)
    h = h.replace('0x', '')
    if len(h) > 4:
        return 'ABCD'
    elif len(h) < 4:
        return '0' * (4 - len(h)) + h
    else:
        return h

# 将16进制转换成10进制
def hex2Int(string_num):
    return int(string_num.upper(), 16)

# 将整数转成16进制
def int2Hex(i, j):
    h = hex(i)
    h = h.replace('0x', '')
    l = j - len(h)
    return '0' * l + h

# 将字节数据转换成字符串
def byte2Hex(bins):
    return ' '.join(["%02X" % x for x in bins])

# 将字符串转成字节数据
def hex2Byte(hexStr):
    return bytes.fromhex(hexStr)

# 将字符串转成16进制字符串
def str2HexStr(s):
    return "".join("{:02x}".format(ord(c)) for c in s)

# 将时间转成字符串格式
def formatTime():
    return time.strftime('%Y%m%d%H%M%S', time.localtime(time.time()))

# 将16进制字符串转成ascii码
def hex2ascii(hex_str):
    a = []
    for i in range(0, len(hex_str), 2):
        b = hex_str[i:i+2]
        a.append(chr(int(b, 16)))
    return ''.join(a)

def insert(sql1, sql2):
    # 打开数据库连接
    db = pymysql.connect(host='127.0.0.1', user='root', password='root', db='management', port=3306)
    # 获取操作游标
    cursor = db.cursor()
    try:
        cursor.execute(sql1)
        result = cursor.fetchall()
        if not result:
            cursor.execute(sql2)
            db.commit()
    except Exception as e:
        db.rollback()
    finally:
        db.close()




