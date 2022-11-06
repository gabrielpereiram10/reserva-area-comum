import { AuthData } from "../@types/auth"
import { ResponseData } from "../@types/http"

const authenticate = (cpf: string, password: string): Promise<ResponseData<AuthData>> => {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (cpf !== "00000000000" || password !== "123456") {
                reject({
                    success: false,
                    message: "Credenciais inválidas."
                })
            }
            resolve({
                success: true,
                data: {
                    token: "token",
                    email: "fulano@gmail.com",
                    name: 'Fulano da Silva',
                }
            })
        }, 1000)
    })
}

export const authService = {
    authenticate
}