#coding=gbk
'''
单项的通讯
'''

import socket
import sys
from time import ctime
import select


class WsCli(object):

    def __init__(self):

        self.cli = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.port = 54321
        self.ip = '192.168.1.105'
        self.cli.connect((self.ip, self.port))

    def connect(self):

        while True:
            data = self.cli.recv(1024)
            print('[%s] %s' %(ctime(), data))

            data = raw_input('>')
            self.cli.send(data)
            if(data == '88'):
                break
        self.cli.close()


if __name__ == '__main__':
    wscl = WsCli()
    wscl.connect()






