import axios from 'axios'
import MenuBar from '../components/MenuBar.vue'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function LibrarianDto (username, password, fullName, isHeadLibrarian){
    this.username = username
    this.password = password
    this.fullName = fullName
    this.isHeadLibrarian = isHeadLibrarian
}

    export default{
     
        methods:  {

            expandLibraryScheduling(){
                const content = document.getElementById("library-schedule")
                const icon = document.getElementById("arrow-library-scheduling")

                if (content.style.display == "none"){
                    content.style.display = "block"
                    icon.innerHTML = "-"
                }

                else {
                    content.style.display = "none"
                    icon.innerHTML = "+"
                }
            },
            expandLibrarianManagement(){
                const content = document.getElementById("librarian-management")
                const icon = document.getElementById("arrow-librarian-management")

                if (content.style.display == "none"){
                    content.style.display = "block"
                    icon.innerHTML = "-"
                }

                else {
                    content.style.display = "none"
                    icon.innerHTML = "+"
                }
            },
            hireLibrarian() {
                let librarianUsername = document.getElementById("librarian-hire-username").value
                console.log(librarianUsername)
                let librarianPassword = document.getElementById("librarian-hire-password").value
                console.log(librarianPassword)
                let librarianFullName = document.getElementById("librarian-hire-fullname").value
                console.log(librarianFullName)

                let goodUrl = "/librarians/create/" + librarianUsername + "?password=" + librarianPassword + "&fullName=" + librarianFullName + "&isHeadLibrarian=false"
                console.log(goodUrl)

                AXIOS.post(goodUrl, {}, {}).then(response => {
                    var t = new LibrarianDto (librarianUsername, librarianPassword, librarianFullName, false)
                    this.titles.push(t)
                    })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorTitle = errorMsg
                      })
            },

            fireLibrarian() {
                let librarianId = document.getElementById("librarian-fire-username").value
                console.log(librarianId)

                let goodUrl = "/librarians/remove/" + librarianId
                console.log(goodUrl)

                AXIOS.post(goodUrl, {}, {}).then(response => {
                    var t = new LibrarianDto (librarianUsername, librarianPassword, librarianFullName, false)
                    this.titles.push(t)
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