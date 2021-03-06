package com.example.StringBootProject;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.UUID;

@RestController
@RequestMapping(value = "/")
public class StudentController {

    MongoClient mongo = new MongoClient("localhost", 27017);
    MongoDatabase database = mongo.getDatabase("EmployeeDetails");
    MongoCollection collection = database.getCollection("StudentData");

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String getStudent() {
        MongoCursor cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Document doc = (Document) cursor.next();
            return doc.toJson();
        }
        return null;
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addNewUsers(@RequestBody Model student) {
        try {
            MongoCursor c = collection.find(Filters.and(Filters.eq("studentName", student.getStudentName()), Filters.eq("cources", student.getCources()))).iterator();
            if (c != null) {
                System.out.println("This Student Already Associated with this course");

            } else {
                Document doc = Document.parse(student.toString());
                doc.put("_id", UUID.randomUUID().toString());
                MongoCursor cursor = (MongoCursor) collection.insertOne(doc);
                return "Student Created";
            }

        } catch (Exception e) {
            System.out.println("Some Exception are occurred");
        }
        return null;
    }

    @RequestMapping(value = "/update/{studentName}", method = RequestMethod.PUT)
    public String updateStudent(@RequestBody Model student, @PathParam("studentName") String studentName) {
        try {
            BasicDBObject bds = new BasicDBObject();
            bds.put("studentName", studentName);
            collection.updateOne(bds, Document.parse(student.toString()));
            return "Student Record updated";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/student/{studentid}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable("studentid") String studenName) {
        try {
            MongoCursor count = (MongoCursor) collection.deleteOne(Filters.eq("studentName", studenName));
            return "Record Deleted Succussfully";
        } catch (Exception e) {
            System.out.println("Some Exception occurred");
        }
        return null;
    }
}
