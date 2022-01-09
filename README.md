Τα SQL queries βρίσκονται στα αρχεία data.sql και schema.sql κάτω από τον
φάκελο resources.</br></br>
Για την εγκατάσταση της εφαρμογής σε Windows(10, 11) ή Linux(Ubuntu), θα χρειαστούν τα ακόλουθα εργαλεία:
- Java JDK 15 (15.0.2): https://www.techspot.com/downloads/5552-java-15-jdk.html
- MySQL Server (8.0.25): https://dev.mysql.com/downloads/mysql/

Αφού εγκαταστήσετε τα παραπάνω, ακολουθούν κάποιες παραμετροποιήσεις.</br>
Ανοίξτε το terminal (Windows, Linux) και γράψτε την εντολή:</br>
**mysql**
</br>
με δικαιώματα διαχειριστή για να ξεκινήσει το session του server. Στη συνέχεια, τρέξτε την εντολή:</br>
**UPDATE mysql.user SET password=admin WHERE user='root';**
</br>
και κλείστε το session.</br>
Έχοντας ετοιμάσει τον server, ακολουθεί η εγκατάσταση και εκτέλεση της εφαρμογής.</br>
Με ανοιχτό το terminal στον κατάλογο που θέλετε να εγκαταστήσετε την εφαρμογή, δώστε τις εντολές:</br>
**git clone https://github.com/manolismavrok/DistributedSystems.git**
</br>
**cd DistributedSystems** (ή '**cd DistributedSystems-main**' ανάλογα την μετονομασία) 
</br>
**java -jar target/DistributedSystems-0.0.1-SNAPSHOT.jar**
</br>
Αυτόματα θα ξεκινήσει το session στο terminal, με προκαθορισμένο port την 8080.</br>
Για τη σύνδεση στην υπηρεσία, ανοίξτε ένα browser με url:</br>
**localhost:8080**
</br>
το οποίο σας κατευθύνει απευθείας στο root που είναι το login.



