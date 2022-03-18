package com.atlas.mygoods.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class PatientService {

    public static final String COL_NAME = "patients";
    private final Firestore dbFirestore = FirestoreClient.getFirestore();

    public String savePatientDetails(Patient patient) throws InterruptedException, ExecutionException {
        final CollectionReference collectionReference = dbFirestore.collection(COL_NAME);
        final ApiFuture<WriteResult> collectionsApiFuture =
                collectionReference.document(patient.getName()).set(patient);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Patient getPatientDetails(String name) throws InterruptedException, ExecutionException {
//        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Patient patient;

        if (document.exists()) {
            patient = document.toObject(Patient.class);
            return patient;
        } else {
            return null;
        }
    }

    public String updatePatientDetails(Patient person) throws InterruptedException, ExecutionException {
//        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(person.getName()).set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deletePatient(String name) {
//        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
        return "Document with Patient ID " + name + " has been deleted";
    }

    public String getFirestoreData() throws InterruptedException, ExecutionException {
//        Firestore dbFirestore = FirestoreClient.getFirestore();
        final DocumentReference documentReference =
                dbFirestore.collection("flutterItems").document("1tAmeAbAaxOZwj1iBeNV");
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.getData().toString();

        } else {
            return null;
        }
    }


}