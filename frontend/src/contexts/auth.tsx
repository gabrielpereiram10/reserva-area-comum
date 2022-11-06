import React, { useContext, useEffect, useState } from "react"
import { createContext } from "react"
import AsyncStorage from '@react-native-async-storage/async-storage'

import { AuthContextData, AuthData, SignData } from "../@types/auth"
import { authService } from "../services/auth"

export const AuthContext = createContext<AuthContextData>({} as AuthContextData)

export const AuthProvider = ({ children }: React.PropsWithChildren<{}>) => {
    const [authData, setAuthData] = useState<AuthData>()
    const [isLoading, setIsLoading] = useState<boolean>(true)

    useEffect(() => {
        loadStorageData()
    }, [])

    const loadStorageData = async (): Promise<void> => {
        try {
            const authDataSerialized = await AsyncStorage.getItem('@AuthData')
            if (authDataSerialized) {
                const _authData: AuthData = JSON.parse(authDataSerialized)
                setAuthData(_authData)
            }
        } catch (error) {
        } finally {
            setIsLoading(false)
        }
    }

    const signIn = async (data: SignData) => {
        return await authService.authenticate(data.cpf, data.password)
            .then(response => {
                const { data, success } = response
                setAuthData(data)
                AsyncStorage.setItem('@AuthData', JSON.stringify(data))
                return { success }
            })
            .catch(err => {
                return {
                    ...err
                }
            })

    }

    const signOut = async () => {
        setAuthData(undefined)
        await AsyncStorage.removeItem('@AuthData')
    }

    return (
        <AuthContext.Provider value={{ authData, isLoading, signIn, signOut }}>
            {children}
        </AuthContext.Provider>
    )
}

export const useAuth = (): AuthContextData => {
    const context = useContext(AuthContext);

    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }

    return context;
}