import bluetooth

server_socket = bluetooth.BluetoothSocket(bluetooth.RFCOMM)

server_socket.bind(("", 7))
server_socket.listen(1)

client_socket, address = server_socket.accept()

data = "continue"
while data == "continue":
    print("lol")

    data = client_socket.recv(1024)

    data = "stop"

client_socket.close()
server_socket.close()

devices = bluetooth.discover_devices(lookup_names=True)

print("found %d devices" % len(devices))
for addr, name in devices:
    print(" %s - %s" % (addr, name))
