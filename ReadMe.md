# <code style = "color:#4C4C6D"><u> RECIPE PROJECT </u> </code>
- Bu projede yemek tarifi uygulaması için, 'mikroservice' mimarisi kullanılarak, back-end kodları yazılmıştır.
- Kullanıcı bu programda sisteme kayıt olabiliyor, giriş yapabiliyor. 
- Sistemde eklenen tarifleri puanlayabiliyor, bu tariflere yorum yapabiliyor. 
- İstedikleri tarifleri favorilerine ekleyebiliyorlar.

## <code style = "color:#1B9C85"><u>Kullanılan yazılım dili: </u> </code>
- Java, Spring Boot

## <code style="color:#1B9C85"> <u> Kullanılan teknolojiler: </u> </code>
- OpenFeign, RabbitMQ, Redis, Zipkin, OpenAPI(SwaggerUI)

## <code style = "color:#1B9C85"><u> Kullanılan veritabanları: </u></code>
- PostgreSQL,MongoDB

## <code style="color:#1B9C85"><u>Kullanılan Service'ler ve Linkleri: </u></code>
* [Auth Service](http://localhost:8060/swagger-ui/index.html)
* [Comment Service](http://localhost:8070/swagger-ui/index.html)
* [Recipe Service](http://localhost:8080/swagger-ui/index.html)
* [UserProfile Service](http://localhost:8090/swagger-ui/index.html)
* Mail Service
* Config Server

## <code style= "color : #FFE194"><u> Auth Service</u></code>
- Kullanıcı işlemleri için kullanılan 'service'dir.
- Kullanıcının kayıt, giriş işlemleri, şifresinin unutulduğunda şifre değişikliği yapabildiği, 
hesabını aktif edebileceği servistir. 
</br>

<code style = "color :E8F6EF"><u>Kayıt Olma</u></code></br>
![Register](SwaggerScreenShot/authservice/auth-register.png) </br></br>
<code style = "color :E8F6EF"><u>Giriş Yapma</u></code></br>
![Login](SwaggerScreenShot/authservice/auth-login.png) </br></br>
<code style = "color E8F6EF"><u>Şifremi Unuttum</u></code></br>
![Forgot Password](SwaggerScreenShot/authservice/auth-forgotpassword.png)

## <code style = "color : #FFE194"><u>User Service</u></code>
- Kullanıcının bilgilerini güncelleyebildiği, şifresini değiştirebildiği, 
tarifleri favorilerine ekleyebildiği servistir.
</br>

<code style = "color E8F6EF"><u>Kullanıcı Güncelleme</u></code></br>
![User Update](SwaggerScreenShot/userservice/user-update.png)</br></br>
<code style = "color E8F6EF"><u>Kullanıcı Şifre Değiştirme</u></code></br>
![User Change Password](SwaggerScreenShot/userservice/user-changepass.png)</br></br>
<code style = "color E8F6EF"><u>Kullanıcı Favorisine Tarif ekleme</u></code></br>
![User Save Favorite Category](SwaggerScreenShot/userservice/user-save-favoriterecipe.png)</br></br>

## <code style = "color : #FFE194"><u> Comment Service </u></code>
- Kullanıcıların tariflere yorum yapabildiği,güncelleyebildiği ve silebildiği; aynı şekilde
tarifleri puanlayabildiği, puanını değiştirebildiği ve puanı silebildiği servistir.
</br>

<code style= "color: E8F6EF"><u>Yorum Ekleme</u></code></br>
![Comment Add](SwaggerScreenShot/commentservice/comment-add.png)</br></br>
<code style = "color :E8F6EF"><u>Yorum Güncelleme</u></code></br>
![Comment Update](SwaggerScreenShot/commentservice/comment-update.png)</br></br>
<code style = "color :E8F6EF"><u>Yorum Silme</u></code></br>
![Comment Delete](SwaggerScreenShot/commentservice/comment-delete.png)</br></br>
<code style ="color : E8F6EF"><u>Puan Ekleme</u></code></br>
![Point Add](SwaggerScreenShot/pointservice/point-add.png)</br></br>
<code style = "color : E8F6EF"><u>Puan Güncelleme</u></code></br>
![Point Update](SwaggerScreenShot/pointservice/point-update.png)</br></br>
<code style = "color : E8F6EF"><u>Puan Silme</u></code></br>
![Point Delete](SwaggerScreenShot/pointservice/point-delete.png)</br></br>

## <code style = "color :#FFE194"><u> Category Service </u></code>
- Yanlızca adminlerin işlem yapabildiği, tarifler için kategori işlemlerinin yapılabildiği servistir.
</br>

<code style = "color :E8F6EF"><u>Kategori Ekleme</u></code></br>
![Category Add](SwaggerScreenShot/categoryservice/category-save.png)</br></br>
<code style = "color :E8F6EF"><u>Kategori Silme</u></code></br>
![Category Delete](SwaggerScreenShot/categoryservice/category-delete.png)</br></br>

## <code style = "color : #FFE194"><u> Recipe Service </u></code>
- Yanlızca adminlerin işlem yapabildiği, tarifler ekleyip silebildiği ve güncelleyebildiği servistir.
</br>

<code style = "color :E8F6EF"><u>Tarif Ekleme</u></code></br>
![Recipe Add](SwaggerScreenShot/recipeservice/recipe-save.png)</br></br>
<code style ="color :E8F6EF"><u>Tarif Güncelleme</u></code></br>
![Recipe Update](SwaggerScreenShot/recipeservice/recipe-update.png)</br></br>
<code style ="color :E8F6EF"><u>Tarifleri Kategoriye göre sıralama</u></code></br>
![Recipe Order By Calories](SwaggerScreenShot/recipeservice/recipe-orderby-calories.png)</br></br>
<code style ="color :E8F6EF"><u>Tarifleri Kategorilerine göre bulme</u></code></br>
![Recipe Search With Category](SwaggerScreenShot/recipeservice/recipe-search-recipe-withcategory.png)</br></br>
<code style = "color :E8F6EF"><u>Tarifleri içeriklerine göre bulma</u></code></br>
![Recipe Search With Ingredients Name](SwaggerScreenShot/recipeservice/recipe-search-recipewith-ingredientsname.png)</br></br>
<code style = "color :E8F6EF"><u>Tarifleri isimlerine göre bulma</u></code></br>
![Recipe Search With Food Name](SwaggerScreenShot/recipeservice/recipe-searchwith-foodname.png)</br></br>