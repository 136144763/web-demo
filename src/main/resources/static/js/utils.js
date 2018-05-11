// const validateUrl = 'http://localhost:8080/validate/customer';

function emailExists() {
    return {
        required: true,
        email: true,
        maxlength: 50,
        remote: {
            url: '/validate/email',
            type: 'post',
            data: {
                value: function () {
                    return $('input[name=email]').val().trim();
                }
            }
        }
    };
}

function verificationCodeRule(validateUrl) {
    return {
        required: true,
        maxlength: 6,
        minlength: 6,
        digits: true,
        remote: {
            url: validateUrl,
            type: 'post',
            data: {
                value: function () {
                    return $('input[name=verificationCode]').val().trim();
                }
            }
        }
    };
}

function emailRule(validateUrl) {
    return {
        required: true,
        email: true,
        maxlength: 50,
        remote: {
            url: validateUrl,
            type: 'post',
            data: {
                field: 'email',
                value: function () {
                    return $('input[name=email]').val().trim();
                }
            }
        }
    };
}

function usernameRule(validateUrl) {
    return {
        required: true, maxlength: 20,
        remote: {
            url: validateUrl,
            type: 'post',
            data: {
                field: 'username',
                value: function () {
                    return $('#username').val().trim();
                }
            }
        }
    };
}

function companyNameRule(status, type, validateUrl) {
    //var value = $('#companyName').val().trim();
    return {
        required: true, maxlength: 50,
        // remote: {
        //     url: validateUrl,//+'?status='+status+'&type='+type+'&field=companyName&value='+$('#companyName').val().trim(),
        //     type: 'post',
        //     data: {
        //         status: status,
        //         type: type,
        //         field: 'companyName',
        //         value: function () {
        //             return $('#companyName').val();
        //         }
        //     }
        // }
    };
}

function nameRule(status, type, validateUrl) {
    return {
        required: true, maxlength: 50,
        // remote: {
        //     url: validateUrl,
        //     type: 'post',
        //     data: {
        //         status: status,
        //         type: type,
        //         field: 'name',
        //         value: function () {
        //             return $('#name').val().trim();
        //         }
        //     }
        // }
    };
}

function enterpriseSupplierRules(status, validateUrl) {
    var rules = {
        companyName: companyNameRule(status, 'enterprise', validateUrl),
        companyAddress: {
            required: true, maxlength: 50
        },
        legalPerson: {
            required: true, maxlength: 50
        },
        legalPersonIdCard: {
            isIdCardNo: true, maxlength: 18
        },
        // uploadImageEnterprise: {
        //     required: true
        // },
        contacts: {required: true, maxlength: 50},
        contactsMobile: {required: true, isMobile: true, maxlength: 11},

        email: emailRule(validateUrl),
        verificationCode: verificationCodeRule('/api/validate/code'), //{required: true, maxlength: 6, minlength: 6, digits: true},
        bankAccountName: {
            required: true, maxlength: 50
        },
        bankName: {
            required: true, maxlength: 50
        },
        bankAccount: {
            required: true, maxlength: 50, number: true
        },
        bankCode: {
            required: true, maxlength: 50
        },

        username: usernameRule(validateUrl),
        password: {
            required: true,
            minlength: 6
        },
        confirmPassword: {
            required: true,
            minlength: 6,
            equalTo: "#password"
        }
    };

    return rules;
}


function enterprisePurchaserRules(status, validateUrl) {
    var rules = {
        companyName: companyNameRule(status, 'enterprise', validateUrl),
        companyAddress: {
            required: true, maxlength: 50
        },
        legalPerson: {
            required: true, maxlength: 50
        },
        legalPersonIdCard: {
            isIdCardNo: true, required: true, maxlength: 18
        },
        uploadImageEnterprise: {
            required: true
        },
        contacts: {required: true, maxlength: 50},
        contactsMobile: {required: true, isMobile: true, maxlength: 11},

        email: emailRule(validateUrl),
        verificationCode: verificationCodeRule('/api/validate/code'), //{required: true, maxlength: 6, minlength: 6, digits: true},
        bankAccountName: {
            required: true, maxlength: 50
        },
        bankName: {
            required: true, maxlength: 50
        },
        bankAccount: {
            required: true, maxlength: 50, number: true
        },
        bankCode: {
            required: true, maxlength: 50
        },

        username: usernameRule(validateUrl),
        password: {
            required: true,
            minlength: 6
        },
        confirmPassword: {
            required: true,
            minlength: 6,
            equalTo: "#password"
        }
    };

    return rules;
}


function personalRule(status, validateUrl) {
    var rules = {
        name: nameRule(status, 'personal', validateUrl),
        email: emailRule(validateUrl),
        verificationCode: verificationCodeRule('/api/validate/code'),
        mobile: {
            required: true, isMobile: true, maxlength: 11
        },
        idCard: {
            required: true, isIdCardNo: true, maxlength: 18
        },
        uploadImagePerson: {
            required: true
        },

        bankAccountName: {
            required: true, maxlength: 50
        },
        bankName: {
            required: true, maxlength: 50
        },
        bankAccount: {
            required: true, maxlength: 50, number: true
        },
        bankCode: {
            required: true, maxlength: 50
        },

        username: usernameRule(validateUrl),
        password: {
            required: true,
            minlength: 6
        },
        confirmPassword: {
            required: true,
            minlength: 6,
            equalTo: "#password1"
        }
    };

    return rules;
}