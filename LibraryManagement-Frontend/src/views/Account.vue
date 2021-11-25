<template>
<div>
    <MenuBar />
    <div class="header">Account Info</div>
    <div class="content">
        <div class="header2">Username: 
            <span class="info username">username</span>
            <input @click="editUsername()" class="edit-btn" type="submit" value="Edit">
            <input class="edit edit-username" placeholder="new username" hidden=true type="text">
        </div>
        <div class="header2">Password: 
            <span class="info password-hidden">*******</span> 
            <span hidden=true class="info password">password</span>
            <input @click="showPass()" type="checkbox" class="show-pass">
            <input @click="editPassword()" class="edit-btn" type="submit" value="Edit">
            <input class="edit edit-password" placeholder="new password" hidden=true type="text">
        </div>
        <div class="header2">Email: 
            <span class="info email">email</span>
            <input @click="editEmail()" class="edit-btn" type="submit" value="Edit">
            <input class="edit edit-email" placeholder="new email" hidden=true type="text">
        </div>
        <div class="header2">Address: 
            <span class="info address">address</span>
            <input @click="editAddress()" class="edit-btn" type="submit" value="Edit">
            <input class="edit edit-address" placeholder="new address" hidden=true type="text">
        </div>
    </div>
    <br>
    <input @click="saveInfo()" hidden=true class="save" type="submit" value="Save">
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
        },

        editUsername(){
            const save = document.querySelector(".save")

            if (save.hidden){
                document.querySelector(".save").hidden = false;
                document.querySelector(".save").className += " fadeIn";
            }
            document.querySelector(".edit-username").hidden = false;
            document.querySelector(".edit-username").className += " fadeIn";
        },

        editPassword(){
            const save = document.querySelector(".save")

            if (save.hidden){
                document.querySelector(".save").hidden = false;
                document.querySelector(".save").className += " fadeIn";
            }
            document.querySelector(".edit-password").hidden = false;
            document.querySelector(".edit-password").className += " fadeIn";
        },

        editEmail(){
            const save = document.querySelector(".save")

            if (save.hidden){
                document.querySelector(".save").hidden = false;
                document.querySelector(".save").className += " fadeIn";
            }
            document.querySelector(".edit-email").hidden = false;
            document.querySelector(".edit-email").className += " fadeIn";
        },

        editAddress(){
            const save = document.querySelector(".save")

            if (save.hidden){
                document.querySelector(".save").hidden = false;
                document.querySelector(".save").className += " fadeIn";
            }
            document.querySelector(".edit-address").hidden = false;
            document.querySelector(".edit-address").className += " fadeIn";
        },

        saveInfo(){
            document.querySelector(".edit-username").hidden = true;
            document.querySelector(".edit-password").hidden = true;
            document.querySelector(".edit-email").hidden = true;
            document.querySelector(".edit-address").hidden = true;

            // must actually update db afterwards
        }
    }
}

</script>
<style scoped>

    .save{
        width: 300px;
        background-color: darkslategray;
        color: aliceblue;
        padding: 5px;
        border-radius: 10px;
        margin-left: 10px;
        font-size: 10px;
    }

    .save:hover{
        width: 300px;
        background-color: aliceblue;
        color: darkslategray;
        padding: 5px;
        border-radius: 10px;
        margin-left: 10px;
        font-size: 10px;
    }

    .edit{
        width: 200px;
        border-radius: 10px;
        font-size: 15px;
        margin-left: 10px;
    }

    .fadeIn{
        animation-name: fadeIn;
        animation-duration: 1s;
    }

    @keyframes fadeIn {
        from {
            opacity: 0;
        }

        to {
            opacity: 1;
        }
    }

    .edit-account{
        float: right;
    }

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
        width: 50px;
        padding: 5px;
        border-radius: 10px;
        margin-left: 10px;
        font-size: 10px;
    }

    .edit-btn:hover {
        background-color: aliceblue;
        color: darkslategray;
        width: 50px;
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
