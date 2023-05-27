# <font color = "#4C4C6D"><u> RECIPE PROJECT </u> </font>
- Bu projede yemek tarifi uygulaması için, 'mikroservice' mimarisi kullanılarak, back-end kodları yazılmıştır.
- Kullanıcı bu programda sisteme kayıt olabiliyor, giriş yapabiliyor. 
- Sistemde eklenen tarifleri puanlayabiliyor, bu tariflere yorum yapabiliyor. 
- İstedikleri tarifleri favorilerine ekleyebiliyorlar.

## <font color = "#1B9C85"><u>Kullanılan yazılım dili: </u> </font>
- Java, Spring Boot

## <font color="#1B9C85"> <u> Kullanılan teknolojiler: </u> </font>
- OpenFeign, RabbitMQ, Redis, Zipkin, OpenAPI(SwaggerUI)

## <font color="#1B9C85"><u> Kullanılan veritabanları: </u></font>
- PostgreSQL,MongoDB

## <font color="#1B9C85"><u>Kullanılan Service'ler ve Linkleri: </u></font>
* [Auth Service](http://localhost:8060/swagger-ui/index.html)
* [Comment Service](http://localhost:8070/swagger-ui/index.html)
* [Recipe Service](http://localhost:8080/swagger-ui/index.html)
* [UserProfile Service](http://localhost:8090/swagger-ui/index.html)
* Mail Service
* Config Server

## <font color = "#FFE194"><u> Auth Service</u></font>
- Kullanıcı işlemleri için kullanılan 'service'dir.
- Kullanıcının kayıt, giriş işlemleri, şifresinin unutulduğunda şifre değişikliği yapabildiği, 
hesabını aktif edebileceği servistir. 
</br>

<font color ="E8F6EF"><u>Register</u></font></br>
![Register](SwaggerScreenShot/authservice/auth-register.png) </br></br>
<font color ="E8F6EF"><u>Login</u></font></br>
![Login](SwaggerScreenShot/authservice/auth-login.png) </br></br>
<font color ="E8F6EF"><u>Forgot Password</u></font></br>
![Forgot Password](SwaggerScreenShot/authservice/auth-forgotpassword.png)

## <font color = "#FFE194"><u>User Service</u></font>
- Kullanıcının bilgilerini güncelleyebildiği, şifresini değiştirebildiği, 
tarifleri favorilerine ekleyebildiği servistir.
</br>

<font color ="E8F6EF"><u>Kullanıcının Bilgilerini Güncelleme</u></font></br>
![User Update](SwaggerScreenShot/userservice/user-update.png)</br></br>
<font color ="E8F6EF"><u>Kullanıcının Şifresini Değiştirme</u></font></br>
![User Change Password](SwaggerScreenShot/userservice/user-changepass.png)</br></br>
<font color ="E8F6EF"><u>Kullanıcının Tarifi Favorilerine ekleme</u></font></br>
![User Save Favorite Category](SwaggerScreenShot/userservice/user-save-favoriterecipe.png)</br></br>

## <font color = "#FFE194"><u> Comment Service </u></font>
- Kullanıcıların tariflere yorum yapabildiği,güncelleyebildiği ve silebildiği; aynı şekilde
tarifleri puanlayabildiği, puanını değiştirebildiği ve puanı silebildiği servistir.
</br>

<font color ="E8F6EF"><u>Yorum Ekleme</u></font></br>
![Comment Add](SwaggerScreenShot/commentservice/comment-add.png)</br></br>
<font color ="E8F6EF"><u>Yorum Güncelleme</u></font></br>
![Comment Add](SwaggerScreenShot/commentservice/comment-update.png)</br></br>
<font color ="E8F6EF"><u>Yorum Silme</u></font></br>
![Comment Add](SwaggerScreenShot/commentservice/comment-delete.png)</br></br>
<font color ="E8F6EF"><u>Puan Ekleme</u></font></br>
![Comment Add](SwaggerScreenShot/pointservice/point-add.png)</br></br>
<font color ="E8F6EF"><u>Puan Güncelleme</u></font></br>
![Comment Add](SwaggerScreenShot/pointservice/point-update.png)</br></br>
<font color ="E8F6EF"><u>Puan Silme</u></font></br>
![Comment Add](SwaggerScreenShot/pointservice/point-delete.png)</br></br>
