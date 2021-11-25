<template>
<div>
<MenuBar />

<div class="header">Hire Librarian</div>

<input class="librarian-info" type="text" placeholder="Username">
                <input class="librarian-info" type="text" placeholder="Password">
                <input class="librarian-info" type="text" placeholder="Full Name">

                 <div>
                <input type="submit" @click="displaySuccess()" class="add-librarian" value="Add">
                <span hidden=true class="header2 success-msg">Title successfully created!</span>
                <span hidden=true class="header2 error-msg">Error message</span>
            </div>

<div class="header">Fire Librarian</div>

</div>
</template>
<script>
import MenuBar from '../components/MenuBar.vue'
export default {
    name: 'Home',
    components: {
    MenuBar
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

    
    </style>