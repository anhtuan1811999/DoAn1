
$(document).ready(function (){

    console.log("Done load js file")

    $('#announce').hide()

    $('#submit').click(function () {
        fetch("/User/login")
            .then(res => {
                if (res.status != 200){

                }
            })
            .catch(err => {
                console.log("err")
            })
    })

})
// async function checkDataLogin (data){
//     await fetch('http://localhost:8086/User/dangnhap',{
//         headers: {
//             'Content-Type':'Application/JSON',
//             'Accept':'Application/JSON'
//         },
//         method:'post',
//         body:JSON.stringify(data)
//     })
//         .then(res => {
//             console.log(res.status)
//         })
//         .catch(err => {
//             console.log('err')
//         })
// }