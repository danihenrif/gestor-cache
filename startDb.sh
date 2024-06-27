echo "------------------------------Creating image of the mongo database..."
sudo docker build -t cache-db:1.0 --target db-img .
echo "------------------------------Killing any instance of mongo if it's running on this machine..."
sudo pkill mongo
echo "------------------------------Running container of the mongo database in another terminal..."
sudo docker run --name cache-db --network="host" -it cache-db:1.0