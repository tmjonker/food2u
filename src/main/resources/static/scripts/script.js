let password1 = document.getElementById("inputPassword1");
let password2 = document.getElementById("inputPassword2");
let passwordsMatch = false;
let submitButton = document.getElementById("submitButton");
let signupForm = document.getElementById("sign-up");

password2.addEventListener("input", function (event) {
    let password1Value = password1.value;
    let password2Value = password2.value;

    if (password2Value != password1Value) {
        password2.setCustomValidity("Both passwords must match.");
        password2.reportValidity();
        passwordsMatch = false;

    } else {
        password2.setCustomValidity("");
        passwordsMatch = true;
    }
});

password1.addEventListener("input", function (event) {
    if (password1.validity.patternMismatch) {
        password1.setCustomValidity("Must be atleast 6 characters, contain 1 uppercase letter, 1 lowercase letter, 1 symbol, and 1 number");
        password1.reportValidity();
        passwordsMatch = true;
    } else {
        password1.setCustomValidity("");
        password1.reportValidity();
    }
});