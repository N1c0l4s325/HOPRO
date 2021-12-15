<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/nav-bar.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/contact.css">
<link rel="stylesheet" href="css/all.css">
</head>
<body>
<div class="principal-container">

    <jsp:include page="nav-bar.jsp"></jsp:include>

        <div class="second-container">
           
   <div class="form-contact-container">
        <form class="form-contact" id="form-contact" action="" method="">
              
                <div class="form-group form-group-name" id="group-name">
                    <label for="name" class="form-label">Nombre</label>
                <div class="form_register-input">
                    <input type="text" name="name" id="name" class="form-input">
                    
                    
                </div>
                <p class="form-state-error">Este campo esta vacio</p>
                 </div>       
                        <div class="form-group  form-group-surname" id="group-surname">
                            <label for="surName" class="form-label">Apellido</label>
                            <div class="form_register-input">
                                <input type="text" name="surName" id="surName" class="form-input">
                              
                            </div>
                            <p class="form-state-error">Este campo esta vacio</p>
                  </div>
                  <div class="form-group form-group-email " id="group-email">
                            <label for="email" class="form-label  form-label-email">Email</label>
                                <div class="form_register-input">
                                    <input type="text" name="email" id="email" class="form-input form-input-email">
                                  
                             </div>
                             <p class="form-state-error">Este campo esta vacio</p>
                        </div>
                        <div class="form-group form-group-coments" id="group-coments">
                            <label for="coments" class="form-label form-label-coments">Consulta o comentario adicional</label>
                                <div class="form_register-input-coments">
                                   <textarea name="coments" id="coments" cols="30" rows="10" class="form-textarea  form-input-textarea"></textarea>
                               </div>
                        </div>

                        <div class="form-group form-group-btn">
                           <button type="submit" class="btn-send">Enviar</button>
                            
                        </div>
                   
         </form>
                </div>


















        </div>


       <jsp:include page="footer.jsp"></jsp:include>

</div>
</body>
</html>