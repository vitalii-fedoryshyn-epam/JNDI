@ECHO OFF
start rmiregistry
echo "RMI registry started"
PAUSE 

start java -cp .;bin Server
echo "Server started"
PAUSE 

start java -cp .;bin Client
echo "Client started"

