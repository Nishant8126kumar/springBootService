package com.example.StringBootProject;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.logging.Filter;

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
           MongoCursor cursor= (MongoCursor) collection.insertOne(Document.parse(student.toString()));
           return "Student Created";

       }catch (Exception e)
       {
           System.out.println("Some Exception are occurred");
       }
       return null;
    }

    @RequestMapping(value = "/update/{studentName}", method = RequestMethod.PUT)
    public String updateStudent(@RequestBody Model student, @PathParam("studentName") String studentName)
    {
        try {
            BasicDBObject bds=new BasicDBObject();
            bds.put("studentName",studentName);
            collection.updateOne(bds,Document.parse(student.toString()));
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
        }catch (Exception e)
        {
            System.out.println("Some Exception occurred");
        }
        return "Record Deleted Successfully";
    }
}
