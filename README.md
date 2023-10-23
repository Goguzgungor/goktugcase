Merhaba incelemenizi daha rahat yapabilmeniz için bu dosyayı oluşturdum.
Öncelikle sistemin nasıl çalıştığını gösteren diagramı aşağıya ekliyorum.

![Diagram](https://i.imgur.com/dq9Nz35.png)

Sistemde 1 Adet Controller bulunmaktadır. Bu controller'ın ismi KafkaController'dır.
İçinde sadece 1 tnae endpoint bulunmaktadır. Bu end point kafka'ya mesaj göndermektedir.Ve sonra gelen veriyi işlemektedir.
Sistemde 4 adet servis bulunmaktadır. Bunlar;
Consumer Service, UserService,Kafka Service ve Producer Service'dir.

Producer Service, Kafka'ya mesaj göndermektedir.
Consumer Service, Kafka'ya gelen mesajları dinlemektedir. Kafka'ya gelen mesajları dinleyip sonra bunları UserService'e göndermektedir.
Daha temiz bir mimari kullanmak amacıyla da Kafka Service'i ekledim. Kafka Service, diğer servicelerin yönetildiği bir service'dir.

Ayrıca MessageState diye bir kısım da ekledim çünkü Kafka'ya gelen mesajların gönderildikten sonra dinlendiğinden emin olmak istedim.
MessageState sayesinde serviceler birbirinin çalışmasını bekleyebilecekler.Kafka'da bir sıkıntı yaşanması halinde de sistemin loop'a girmemesi için Message State kullanırken sistemin sadece belli bir süre beklemesini sağladım o yüzden sorunsuz çalışıyor.

Toplantı yaptığımzda daha da ayrıntılı konuşabiliriz. İyi çalışmalar.




