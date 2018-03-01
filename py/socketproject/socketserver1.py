import socketserver
import socket
from constants import *
import time

# 监听地址
addr = ('0.0.0.0', 12306)
# 客户端地址列表
client_addr = []
# 客户端连接列表
client_socket = []
# 客户端注册号
client_num_addr = []
# 保存06的连接
client_06 = []

class MyServer(socketserver.BaseRequestHandler):

    ip = ''
    port = 0
    # 设置超时时间变量
    timeOut = 120
    id = ''
    model = ''
    version = ''

    def setup(self):
        # 获取客户端的ip
        self.ip = self.client_address[0].strip()
        # 获取客户端 port
        self.port = self.client_address[1]
        # 对socket设置超时时间
        self.request.settimeout(self.timeOut)
        print(self.ip + ":" + str(self.port) + '连接到服务器')

    def handle(self):
        while True:
            time.sleep(1)
            try:
                byteData = self.request.recv(1024)
            except socket.timeout:
                print(self.ip + ':' + str(self.port) + '接收超时，即将断开')
                break
            if byteData:
                if len(byteData) >= 12:
                    strData = byte2Hex(byteData)
                    print('接收到' + self.ip + str(self.port) + '的数据：' + strData)
                    listData = strData.split(' ')
                    strData = strData.replace(' ', '')
                    # 主函数码
                    main_code = listData[4]
                    # 次函数码
                    sub_code = listData[5]
                    # 共同头部
                    top_code = strData[:20]
                    # 设备功能
                    if main_code == '01':
                        # 注册
                        if sub_code == '01':
                            self.id = strData[24:56]
                            self.model = hex2ascii(strData[56:60])
                            self.version = hex2ascii(strData[60:92])
                            client_addr.append(self.client_address)
                            client_socket.append(self.request)
                            client_num_addr.append((self.id, self.request))
                            # 写入数据库
                            print(self.id)
                            insert("select * from ManageSystem_device where device_id = '%s'" % self.id, "insert into ManageSystem_device(device_id, device_version, software_version, device_state, add_time) values('%s', '%s', '%s', '%s', '%s')" % (self.id, self.model, self.version, '1', time.strftime("%Y-%m-%d %X", time.localtime())))
                            # 发送数据给设备
                            data_0101 = top_code + length_0101 + '01' + str2HexStr(formatTime()) + last
                            self.request.sendall(hex2Byte(data_0101))
                            print('Server 0101 Send：' + data_0101)
                        # 心跳
                        elif sub_code == '02':
                            self.request.sendall(hex2Byte(top_code + length_0102 + last))
                        # 重启 48484A4A 01 04 00000004 0010 设备id 4A4A4848
                        elif sub_code == '04':
                            if self.request not in client_socket:
                                r_0104 = self.get_request(strData[24:56])
                                data_0104 = top_code + length_0104 + last
                                if r_0104:
                                    r_0104.sendall(hex2Byte(data_0104))
                                    print('Server 0104 Send：' + data_0104)
                            else:
                                pass
                        # 开关控制 48484A4A 01 05 00000005 0014 设备id 开关控制量 4A4A4848
                        elif sub_code == '05':
                            if self.request not in client_socket:
                                r_0105 = self.get_request(strData[24:56])
                                data_0105 = top_code + length_0105 + strData[56:64] + last
                                if r_0105:
                                    r_0105.sendall(hex2Byte(data_0105))
                                    print('Server 0105 Send：' + data_0105)
                            else:
                                pass
                        # 开关状态 48484A4A 01 06 00000006 0010 设备id 4A4A4848
                        elif sub_code == '06':
                            if strData[20:24] == '0010':
                                client_06.append(self.request)
                            else:
                                for c in client_06:
                                    c.sendall(byteData)
                                    print('Server 0106 Send：' + strData)
                            if self.request not in client_socket:
                                r_0106 = self.get_request(strData[24:56])
                                data_0106 = top_code + length_0106 + last
                                if r_0106:
                                    r_0106.sendall(hex2Byte(data_0106))
                                    print('Server 0106 Send：' + data_0106) 
                        else:
                            pass
                    # 远程升级
                    elif main_code == '05':
                        # 查询版本
                        if sub_code == '01':
                            data_0501 = top_code + length_0501 + version + last
                            self.request.sendall(hex2Byte(data_0501))
                            print('Server 0501 Send：' + data_0501)
                        # 请求升级（主动）
                        elif sub_code == '02':
                            file_size, package_size = getFileSize()
                            data_0502 = top_code + length_0502 + '01' + int2Hex(file_size, 8) + int2Hex(package_size, 4) + last
                            self.request.sendall(hex2Byte(data_0502))
                            print('Server 0502 Send：' + data_0502)
                        # 发起升级（被动）
                        elif sub_code == '03':
                            if self.request not in client_socket:
                                r_0503 = self.get_request(strData[24:88])
                                data_0503 = top_code + length_0503 + '01' + version + int2Hex(file_size, 8) + int2Hex(package_size, 4) + last
                                if r_0503:
                                    r_0503.sendall(hex2Byte(data_0503))
                                    print('Server 0503 Send：' + data_0503)
                            else:
                                pass
                        # 升级包发送
                        elif sub_code == '04':
                            package_size_code = listData[12] + listData[13]
                            package_num_code = listData[14] + listData[15]
                            d_package_size = hex2Int(package_size_code)
                            d_package_num = hex2Int(package_num_code)
                            s_file_size, s_package_size = getFileSize()
                            if d_package_size == s_package_size:
                                b_data = readFile(d_package_num, d_package_size)
                                hex_data = byte2Hex(b_data).replace(' ', '')
                                data_0504 = top_code + int2Hex(len(b_data) + 8, 4) + package_size_code + package_num_code + int2Hex(len(b_data), 4) + byte2Hex(b_data).replace(' ', '') + crc_16(b_data) + last
                                self.request.sendall(hex2Byte(data_0504))
                                print('Server 0504 Send：' + data_0504)
                            pass
                        else:
                            pass
                    else:
                        pass
            else:
                break

    def get_request(self, n_id):
        for c in client_num_addr:
            if c[0] == n_id:
                for s in client_socket:
                    if c[1] == s:
                        return s
                    else:
                        return None

    def finish(self):
        print(self.ip + ':' + str(self.port) + '已经断开连接')
        if self.client_address in client_addr:
            client_addr.remove(self.client_address)
        if self.request in client_socket:
            client_socket.remove(self.request)
        if (self.id, self.request) in client_num_addr:
            client_num_addr.remove((self.id, self.request))
        if self.request in client_06:
            client_06.remove(self.request)

if __name__ == '__main__':
    sever = socketserver.ThreadingTCPServer(addr, MyServer)
    sever.serve_forever()
