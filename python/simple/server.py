import socket
s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)

host = socket.gethostbyname('localhost')

port = 54321
print host
s.bind((host,port))

s.listen(5)
while True:
    c,addr = s.accept()
    print('Got connection from ',addr)
    data = c.recv(1024)
    print(addr,' say: ',data)
    #c.send('Thanks for connecting')
    c.send(str(int(data)*2)+'\n')
    c.close()
