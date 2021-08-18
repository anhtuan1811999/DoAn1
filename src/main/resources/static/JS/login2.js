async function login(data) {
    await fetch("/home", {
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        method: 'post',
        body: JSON.stringify(data)
    })
        .then(res => {
            if (res.status === 200){
                window.location.assign('/home')
            } else {
                $('#note').show()
            }
            console.log(res.status)
            console.log(res.statusText)
        })
        .catch(err => {
            console.log('err')
        })

}
$(document).ready(function (){
    $('#note').hide()
    $('#login').click(function (){
        var username = $('#username').val()
        var password = $('#password').val()

        var data = {
            username : username,
            password : password
        }
        login(data)
    })
})