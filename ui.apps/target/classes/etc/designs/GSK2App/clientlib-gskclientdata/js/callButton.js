document.addEventListener("click", myFunction);
function myFunction(event){
    console.log("Eventn Checking :",event);
   let targetElement = event.target.tagName;
   if(targetElement === 'BUTTON'){
       console.log("Button call :",event.target.id);
       testCall();
   }

}