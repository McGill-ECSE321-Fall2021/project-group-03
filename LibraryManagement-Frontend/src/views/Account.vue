<template>
<div>
    <MenuBar />
    <div class="header">Account Info</div>
    <div class="content">
        <div class="header2">Username: <span class="info username">username</span></div>
        <div class="header2">Password: 
            <span class="info password-hidden">*******</span> 
            <span hidden=true class="info password">password</span>
        <input @click="showPass()" type="checkbox" class="show-pass"></div>
        <div class="header2">Email: <span class="info email">email</span></div>
        <div class="header2">Address: <span class="info address">address</span></div>
        <input class="edit-btn" type="submit" value="Edit">
    </div>
    <div class="header">Current Holdings</div>
    <div class="content"></div>
    <div class="header">Current Reservations</div>
    <div class="content"></div>
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
        showPass(){
            const password = document.querySelector(".show-pass")
            const passwordHide = document.querySelector(".password-hidden")
            const passwordContent = document.querySelector(".password")
            if (password.checked){
                // display password
                passwordContent.hidden = false
                passwordHide.hidden = true;
            }

            else {
                // hide password
                passwordContent.hidden = true;
                passwordHide.hidden = false;
            }
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

    .header2 {
        color: darkslategray;
        font-weight: 700;
        padding: 10px;
        font-size: 20px;
        padding-bottom: 10px;
    }

    .info {
        font-weight: 400;
        color: teal;
    }

    .content {
        color: teal;
        padding-left: 10px;
        line-height: 1.6;
    }

    body {
        background-color: aliceblue;
    }

    .edit-btn {
        background-color: darkslategrey;
        color: aliceblue;
        width: 100px;
        padding: 5px;
        border-radius: 10px;
        margin-left: 10px;
    }

    .edit-btn:hover {
        background-color: aliceblue;
        color: darkslategray;
        width: 100px;
        padding: 5px;
        border-radius: 10px;
        margin-left: 10px;
    }

    .more-filters-btn {
        color: darkslategray;
        margin-bottom: 10px;
        margin-top: 10px;
        border-radius: 10px;
    }

    .title-name {
        border-radius: 6px;
        width: 500px
    }

    .filter-input {
        border-radius: 6px;
        width: 200px;
        margin-left: 10px;
    }

</style>
