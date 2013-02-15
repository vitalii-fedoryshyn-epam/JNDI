@ECHO OFF
start rmiregistry
echo "RMI registry started"
PAUSE 

start java -cp .;bin StockWatcherServer
echo "StockWatcherServer started"
PAUSE 

start java -cp .;bin StockListenerClient 24000
echo "StockListenerClient 1 started"

start java -cp .;bin StockListenerClient 124000
echo "StockListenerClient 2 started"
