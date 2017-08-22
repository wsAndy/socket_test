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
            self.cli.send('server connect...\n')
            self.cli.settimeout(60)
            tmp = ''

            while True:
                try:
                    data = self.cli.recv(1024)
                    if(data == ""):
                        break;tmp = '88'
                    print('[%s] says: %s' % (ctime(), data))
                    self.cli.send('get at time: [%s]' % ctime() + '\n')

                    if( data == '88'):
                        tmp = '88'
                        break;
                except:
                    self.cli.close()
            print('[%s]: a client leave'%ctime())
            #
            # if(tmp == '88'):
            #     break
        self.sock.close()

if __name__ == '__main__':
    server = WsSocket()
    server.listen()
