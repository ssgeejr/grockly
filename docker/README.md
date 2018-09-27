# MongoDB beckend setup & configuration

first start the Docker instance
```
docker run --rm --name groklydb -ti -v /opt/grokly/db:/data/db -d mongo:4.0.2 
```

#ensure that the port is mapped if this is the initialization step, we will use the internal network alias for the webapp#
```
docker run --rm --name groklydb -ti -v /opt/grokly/db:/data/db -p 27017:27017 -d mongo:4.0.2 
```

Next copy the files to the /tmp directory

```
docker cp mimport groklydb:/tmp
docker cp link.json groklydb:/tmp
```

Create the database & collection and import the json file
```
docker exec -ti groklydb /tmp/mimport
```

The mimport file should manage your db/collection/record-0 creation
```
mongoimport --db grokly --collection links --type json --file /tmp/link.json
```
