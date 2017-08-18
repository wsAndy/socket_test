
import socket

s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
host = socket.gethostbyname('localhost')
#host = '127.0.0.1'
port = 12345
s.connect((host,port))
s.send("12\n") # you need to add \n
print(s.recv(1024))
s.close
