import { ResponseData } from "./http"

export type AuthContextData = {
    authData?: AuthData
    isLoading: boolean
    signIn(data: SignData): Promise<ResponseData<null>>
    signOut(): void
}

export type AuthData = {
    token: string
    email: string
    name: string
}

export type SignData = {
    cpf: string
    password: string
}