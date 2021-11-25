<template>
    <div>
        <MenuBar />
        <div class="header">Create Title</div>
        <div class="content">
            <form action="">
                <input class="title-info" type="text" placeholder="Name">
                <input class="title-info" type="text" placeholder="Description">
                <input class="title-info" type="text" placeholder="Genre">
                <select class="title-info title-type" name="type">
                        <option value="book">Book</option>
                        <option value="movie">Movie</option>
                        <option value="music-album">Music Album</option>
                        <option value="newspaper">Newspaper</option>
                        <option value="archive">Archive</option>
                </select>
            </form>
            <br>
            <div>
                <input type="submit" @click="displaySuccess()" class="create-title" value="Create Title">
                <span hidden=true class="header2 success-msg">Title successfully created!</span>
                <span hidden=true class="header2 error-msg">Error message</span>
            </div>
        </div>
        <div class="header">Staff Schedule</div>
        <div class="content">SCHEDULE HERE</div>
    </div>
</template>
<script>
import MenuBar from '../components/MenuBar.vue'

export default {
    components: {
        MenuBar,
    },

    methods: {
        displaySuccess(){
            const successMsg = document.querySelector(".success-msg")
            const errorMsg = document.querySelector(".error-msg")
            const error = true
            if (error){
                errorMsg.hidden = false
                errorMsg.className += " fadeIn"
            }
            else {
                successMsg.hidden = false
                successMsg.className += " fadeIn"
            }
            const getinfo = document.getElementsByClassName("title-info")
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

<style scoped>

  .header {
    color: darkslategray;
    font-size: 35px;
    font-weight: 700;
    padding: 10px;
  }

  .create-title {
      padding: 10px;
      margin: 10px;
      border-radius: 10px;
      background-color: darkslategray;
      color: aliceblue;
      width: 200px;
  }

  .fadeIn{
      animation-name: fadeIn;
      animation-duration: 0.5s;
  }

  .error-msg.header2{
      color: crimson;
      padding-left: 15px;
  }

  .success-msg.header2 {
      padding-left: 15px;
      color: forestgreen;
  }

  @keyframes fadeIn {

      from {
          opacity: 0
        };
      to {
          opacity: 1
        };
  }

  .create-title:hover {
      padding: 10px;
      margin: 10px;
      border-radius: 10px;
      background-color: aliceblue;
      color: darkslategray;
      width: 200px;
  }

    .title-info{
        padding: 10px;
        margin: 10px;
        border-radius: 10px;
    }

    .title-type{
        width: 200px;
        padding: 13px;
    }

    .header2 {
        color: darkslategray;
        font-weight: 700;
        padding-bottom: 10px;
        padding: 10px;
    }

</style>
