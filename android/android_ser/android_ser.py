#-*-coding:utf-8-*
#coding=gbk
'''
单项的通讯
'''


import socket
import select
import sys
from time import ctime


class WsSocket(object):

    def __init__(self):
        self.host = '192.168.1.105'
        self.port = 54321
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.bind((self.host,self.port))

    def listen(self):
        self.sock.listen(5)

        while True:
            self.cli, self.addr = self.sock.accept()
#            self.cli.send('server connect...\n')
            self.cli.settimeout(60)
            data = self.cli.recv(1024)
            if data == "":
                pass
            else:
                self.cli.send(self.fun_cal(data)+'\n')
            self.cli.close()
            if data == "88":
                print('leave')
                break
        self.sock.close()

    def fun_cal(self,data):
        return str(int(data)*2)


if __name__ == '__main__':
    server = WsSocket()
    server.listen()
