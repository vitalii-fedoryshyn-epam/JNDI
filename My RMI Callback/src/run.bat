@ECHO OFF
start rmiregistry
echo "RMI registry started"
PAUSE 

start java -cp .;bin server.Server
echo "Server started"
PAUSE 

start java -cp .;bin client.Client
echo "Client 1 started"

start java -cp .;bin client.Client
echo "Client 2 started"
