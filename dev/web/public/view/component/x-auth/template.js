export default function(){
    return `
    <div class="main">  	
        <input type="checkbox" id="chk" aria-hidden="true">
    
            <div class="signup">
                <div>
                    <label for="chk" aria-hidden="true">Sign up</label>
                    <input type="text" placeholder="Login">
                    <input type="email" placeholder="Email">
                    <input type="password" placeholder="Password">
                    <button>Sign up</button>
                </div>
            </div>
    
            <div class="login">
                <div>
                    <label for="chk" aria-hidden="true">Login</label>
                    <input type="text" placeholder="Login">
                    <input type="password" placeholder="Password">
                    <button>Login</button>
                </div>
            </div>
    </div>
    <link rel="stylesheet" href="./public/view/component/x-auth/style.css">
    `
}