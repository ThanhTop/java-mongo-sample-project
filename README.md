1. Build MongoDB
mongod --dbpath=C:\mongodb-win32-x86_64-2.6.12\mongodb-win32-x86_64-2.6.12\data
2. Generate test data
mongo
db.testData.insert({"name" : "sue", "age" : 26, "status" : "A", "group" : [ "news", "sports" ]})
4. Run
