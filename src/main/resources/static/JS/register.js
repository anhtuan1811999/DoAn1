$(document).ready( () => {
    $('#send-data').click(()=>{
        var data = {
            full_name : $('#full-name-input').val(),
            date : $('#date-input').val(),
            address : $('#address-input').val(),
            phone_number : $('#phone-number-input').val(),
            email : $('#email-input').val(),
            user_name : $('#user-name-input').val(),
            password : $('#password-input').val()
        }

        register(data)
    })
})

async function register (data) {
    await fetch('/user/HandleRegister', {
        headers: {
            "Content-Type":"Application/JSON",
            "Accept":"Application/JSON"
        },
        method:'post',
        body: JSON.stringify(data)
    }).then( res => {
        var stt = res.status
        if (stt == 200) {
            alert('tài khoản đă được tạo thành công')
        } else {
            throw new Error()
        }
    })
        .catch(err => {
            console.log('đã gặp sự cố trong quá trình tạo tài khoản')
        })
}