package vn.ntqsolution;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import vn.ntqsolution.constant.MongoDB;
import vn.ntqsolution.entity.User;

public class MongoDBSample {
    public static void main(String[] args) {
        System.out.println("Start program...");
        MongoClientURI uri = new MongoClientURI(MongoDB.CONNECTION_URI);

        // Connect to cluster
        try (MongoClient mongoClient = new MongoClient(uri)) {
            System.out.println("connect successfull");

            // Access a database and testdata collection
            MongoDatabase database = mongoClient.getDatabase(MongoDB.DATABASE_NAME);
            MongoCollection<Document> testDataCollection = database.getCollection(MongoDB.TESTDATA_COLLECTION);

            // Find operation
            Document query = new Document("name", "sue");
            Document result = testDataCollection.find(query).first();
            System.out.println(result.toJson());

            // Insert operation
            Document employeeDocument = new Document("name", "ThanhNguyen")
                    .append("age", 22)
                    .append("role", "dev");
            testDataCollection.insertOne(employeeDocument);

            // Update operation
            Document updateDocument = new Document("role", "dev");
            Document updateCondition = new Document("$set", new Document("role", "tester"));
            UpdateResult updateResult = testDataCollection.updateOne(updateDocument, updateCondition);
            if (updateResult != null)
                System.out.println("Updated Document: " + updateResult.toString());
            else
                System.out.println("No document found with the given criteria.");

            // Delete operation
            Document deleteDocument = new Document("name", "mongo");
            DeleteResult deleteResult = testDataCollection.deleteOne(deleteDocument);

            // Insert user to users collection
            DB db = mongoClient.getDB(MongoDB.DATABASE_NAME);
            User user = User.createUser();
            DBObject doc = createDBObject(user);
            DBCollection col = db.getCollection(MongoDB.USERS_COLLECTION);
            col.insert(doc);

            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static DBObject createDBObject(User user) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("_id", user.getId());
        docBuilder.append("name", user.getName());
        docBuilder.append("role", user.getRole());
        docBuilder.append("isEmployee", user.isEmployee());
        return docBuilder.get();
    }
}
