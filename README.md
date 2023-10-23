Merhaba incelemenizi daha rahat yapabilmeniz için bu dosyayı oluşturdum.
Öncelikle sistemin nasıl çalıştığını gösteren diagramı aşağıya ekliyorum.

![Diagram](https://i.imgur.com/dq9Nz35.png)

Sistemde 1 Adet Controller bulunuyor, KafkaController.
İçinde sadece 1 tane endpoint var. Bu endpoint kafka'ya mesaj göndermektedir ve sonra gelen veriyi işlemektedir.
Sistemde 4 adet servis bulununuyor. Bunlar;
Consumer Service, UserService,Kafka Service ve Producer Service.

Producer Service, Kafka'ya mesaj gönderiyor.
Consumer Service, Kafka'ya gelen mesajları dinleyip sonra bunları UserService'e gönderiyor ve Db işlemleri gerçekleşiyor.
Daha temiz bir mimari kullanmak amacıyla da diğer serviceleri yönettiğim bir service olan Kafka Service'i ekledim.

Ayrıca MessageState diye bir kısım da ekledim çünkü Kafka'ya gelen mesajların gönderildikten sonra dinlendiğinden emin olmak istedim.
MessageState sayesinde serviceler birbirinin çalışmasını bekliyorlar.Kafka'da bir sıkıntı yaşanması halinde de sistemin loop'a girmemesi için Message State kullanırken sistemin sadece belli bir süre beklemesini sağladım o yüzden sorun çıkmayacaktır.

Toplantı yaptığımzda daha ayrıntılı anlatabilirim. İyi çalışmalar.




