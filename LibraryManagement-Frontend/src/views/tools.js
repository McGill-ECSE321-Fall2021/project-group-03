//import axios from 'axios'
import MenuBar from '../components/MenuBar.vue'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function TitleDto (name, description, genre, isAvailable, titleType){
    this.name = name
    this.description = description
    this.genre = genre
    this.isAvailable = isAvailable
    this.titleType = titleType
}

    export default{
     
        methods:  {
            createTitle() {
                let titleName = document.getElementById("title-name-create").value
                console.log(titleName)
                let description = document.getElementById("title-description-create").value
                console.log(description)
                let genre = document.getElementById("title-genre-create").value
                console.log(genre)
                let type = document.getElementById("title-type-create").value
                console.log(type)

                let goodUrl = "/titles/create/" + titleName + "?description=" + description + "&genre=" + genre + "&isAvailable=true" + "&titleType=" + type
                console.log(goodUrl)
                AXIOS.post(goodUrl, {}, {}).then(response => {
                var t = new TitleDto(titleName, description, genre, true, type)
                this.titles.push(t)
                this.newTitle = ''
                })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorTitle = errorMsg
                  })
            },

            expandInventory(){
                const content = document.getElementById("inventory")
                if (content.style.display == "none"){
                    content.style.display = "block"
                }

                else {
                    content.style.display = "none"
                }
            },

            expandStaffSchedule(){
                const content = document.getElementById("staff-schedule")
                if (content.style.display == "none"){
                    content.style.display = "block"
                }

                else {
                    content.style.display = "none"
                }
            },

            expandCheckout(){
                const content = document.getElementById("checkout-title")
                if (content.style.display == "none"){
                    content.style.display = "block"
                }

                else {
                    content.style.display = "none"
                }
            },

            displaySuccess(){
                

                // const successMsg = document.querySelector(".success-msg")
                // const errorMsg = document.querySelector(".error-msg")
                // const error = false
                // if (error){
                //     errorMsg.hidden = false
                //     errorMsg.className += " fadeIn"
                // }
                // else {
                //     successMsg.hidden = false
                //     successMsg.className += " fadeIn"
                // }
                // const getinfo = document.getElementsByClassName("title-info")
                // console.log(getinfo)
                // Array.from(getinfo).forEach(element => {
                //     if(element.tagName != 'SELECT' ){
                //         element.value = ""
                //     }
                // })
                
            },
            deleteTitle() {
                let titleId = document.getElementById("title-id-delete").value
                let goodUrl = "/titles/remove/" + titleId
                
                    console.log(goodUrl)
                    AXIOS.post(goodUrl, {}, {}).then(response => {
                    })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorTitle = errorMsg
                    })
            },
            updateTitle() {
                let titleName = document.getElementById("title-name-update").value
                console.log(titleName)
                let titleDescription = document.getElementById("title-description-update").value
                console.log(titleDescription)
                let titleType = document.getElementById("title-type-update").value
                console.log(titleType)
                let titleGenre = document.getElementById("title-genre-update").value
                console.log(titleGenre)

                let goodUrl = "/titles/update/" + titleName + "/?description=" + titleDescription + "&genre=" + titleGenre + "&titleType=" + titleType
                console.log(goodUrl)
                AXIOS.post(goodUrl, {}, {}).then(response => {
                })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorTitle = errorMsg
                })
            }
    
        },   
        components: {
            MenuBar,
        }
}