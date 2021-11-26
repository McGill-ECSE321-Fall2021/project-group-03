<template>
<div>
<MenuBar />

<div class="header">Hire Librarian</div>

<input class="librarian-info" type="text" placeholder="Username">
                <input class="librarian-info" type="text" placeholder="Password">
                <input class="librarian-info" type="text" placeholder="Full Name">

                 <div>
                <input type="submit" @click="displaySuccessH()" class="add-librarian" value="Hire">
                <span hidden=true class="header2 success-msgH">Librarian Successfully Hired!</span>
                <span hidden=true class="header2 error-msgH">Error message</span>
            </div>

<div class="header">Fire Librarian</div>

<input class="librarian-info" type="text" placeholder="Username">
<div>
                <input type="submit" @click="displaySuccessF()" class="delete-librarian" value="Fire">
                <span hidden=true class="header2 success-msgF">Librarian Successfully Fired.</span>
                <span hidden=true class="header2 error-msgF">Error message</span>
</div>

<div class="header">Set Library Schedule</div>
<div class="header2">Start Time</div>
                <input id="ts" type="time"
       min="09:00" max="22:00" required>
<div class="header2">End Time</div>
 <input id="te" type="time"
       min="09:00" max="22:00" required>

                <div class="header2">Date</div>
<input class="librarian-info" type="text" placeholder="Year">
                <input class="librarian-info" type="text" placeholder="Month">
                <input class="librarian-info" type="text" placeholder="Day">
<div>
                <input type="submit" @click="displaySuccessSET()" class="set-schedule" value="Set">
                <span hidden=true class="header2 success-msgSET">Library Schedule Successfully Set.</span>
                <span hidden=true class="header2 error-msgSET">Error message</span>
            </div>
             </div>

</template>
<script>
import MenuBar from '../components/MenuBar.vue'
export default {
    name: 'Home',
    components: {
    MenuBar
    },
    methods: {
        displaySuccessH(){
            const successMsg = document.querySelector(".success-msgH")
            const errorMsg = document.querySelector(".error-msgH")
            const error = false
            if (error){
                errorMsg.hidden = false
                errorMsg.className += " fadeIn"
            }
            else {
                successMsg.hidden = false
                successMsg.className += " fadeIn"
            }
            const getinfo = document.getElementsByClassName("librarian-info")
            console.log(getinfo)
            Array.from(getinfo).forEach(element => {
                if(element.tagName != 'SELECT' ){
                    element.value = ""
                }
            });
            
        },

        displaySuccessF(){
            const successMsg = document.querySelector(".success-msgF")
            const errorMsg = document.querySelector(".error-msgF")
            const error = false
            if (error){
                errorMsg.hidden = false
                errorMsg.className += " fadeIn"
            }
            else {
                successMsg.hidden = false
                successMsg.className += " fadeIn"
            }
            const getinfo = document.getElementsByClassName("librarian-info")
            console.log(getinfo)
            Array.from(getinfo).forEach(element => {
                if(element.tagName != 'SELECT' ){
                    element.value = ""
                }
            });
            
        },
         displaySuccessSET(){
            const successMsg = document.querySelector(".success-msgSET")
            const errorMsg = document.querySelector(".error-msgSET")
            const error = false
            if (error){
                errorMsg.hidden = false
                errorMsg.className += " fadeIn"
            }
            else {
                successMsg.hidden = false
                successMsg.className += " fadeIn"
            }

             document.getElementById("te").value = document.getElementById("te").defaultValue;
             document.getElementById("ts").value = document.getElementById("ts").defaultValue;
            const getinfo = document.getElementsByClassName("librarian-info")
            console.log(getinfo)
            Array.from(getinfo).forEach(element => {
                if(element.tagName != 'SELECT' ){
                    element.value = ""
                }
            });
            
        }

    }
}
</script>

<style>

  .header {
        color: darkslategray;
        font-size: 35px;
        font-weight: 700;
        padding: 10px;
    }

    .header2 {
        color: darkslategray;
        font-size: 20px;
        font-weight: 700;
        padding: 10px;
    }
     input[type=time] {
        color: darkslategray;
        font-size: 18px;
        font-weight: 700;
        padding: 10px;
        border-radius: 10px;
    }
.librarian-info{
        padding: 10px;
        margin: 10px;
        border-radius: 10px;
    }

      .add-librarian {
      padding: 10px;
      margin: 10px;
      border-radius: 10px;
      background-color: darkslategray;
      color: aliceblue;
      width: 200px;
  }

   .add-librarian:hover {
      padding: 10px;
      margin: 10px;
      border-radius: 10px;
      background-color: aliceblue;
      color: darkslategray;
      width: 200px;
  }

    .delete-librarian {
      padding: 10px;
      margin: 10px;
      border-radius: 10px;
      background-color: darkslategray;
      color: aliceblue;
      width: 200px;
  }

   .delete-librarian:hover {
      padding: 10px;
      margin: 10px;
      border-radius: 10px;
      background-color: aliceblue;
      color: darkslategray;
      width: 200px;
  }

   .fadeIn{
      animation-name: fadeIn;
      animation-duration: 0.5s;
  }

  .error-msgH.header2{
      color: crimson;
      padding-left: 15px;
  }

  .success-msgH.header2 {
      padding-left: 15px;
      color: forestgreen;
  }

   .error-msgF.header2{
      color: crimson;
      padding-left: 15px;
  }

  .success-msgF.header2 {
      padding-left: 15px;
      color: forestgreen;
  }


   .error-msgSET.header2{
      color: crimson;
      padding-left: 15px;
  }

  .success-msgSET.header2 {
      padding-left: 15px;
      color: forestgreen;
  }

   .set-schedule {
      padding: 10px;
      margin: 10px;
      border-radius: 10px;
      background-color: darkslategray;
      color: aliceblue;
      width: 200px;
  }

   .set-schedule:hover {
      padding: 10px;
      margin: 10px;
      border-radius: 10px;
      background-color: aliceblue;
      color: darkslategray;
      width: 200px;
  }

  @keyframes fadeIn {

      from {
          opacity: 0
        };
      to {
          opacity: 1
        };
  }


    
    </style>