import React, { useState } from "react";
import { ActivityIndicator, Alert } from "react-native";
import { Button, FormControl, Icon, IconButton, Input, Link, Stack, Text } from "native-base";
import { MaterialIcons } from "@expo/vector-icons"
import { useNavigation } from "@react-navigation/native";
import * as yup from 'yup'
import { Formik } from "formik";

import { useAuth } from "../../contexts/auth";
import { SignData } from "../../@types/auth";


const initialValues: SignData = {
    cpf: "",
    password: ""
}

const validationSchema = yup.object().shape({
    cpf: yup.string()
        .required()
        .min(11)
        .max(11),
    password: yup.string().required()
})

export const SignForm = () => {
    const [showPassword, setShowPassword] = useState<boolean>(true)
    const [isLoading, setIsLoading] = useState<boolean>(false)

    const { signIn } = useAuth()
    const navigation = useNavigation()

    function changePasswordVisibility() {
        setShowPassword(!showPassword)
    }

    async function handleSubmit(values: any) {
        setIsLoading(true)
        const result = await signIn(values)
        setIsLoading(false)
        if (!result.success) Alert.alert("Erro!", result.message)
    }

    return (
        <Formik
            initialValues={initialValues}
            validationSchema={validationSchema}
            onSubmit={handleSubmit}
        >
            {({
                handleChange,
                handleBlur,
                errors,
                touched,
                handleSubmit,
                values,
            }) => (
                <Stack space={10} w="full" alignItems="center">
                    <Stack space={3} w="full" alignItems="center">
                        <FormControl isInvalid={Boolean(errors.cpf && touched.cpf)}>
                            <Input
                                p={"3"}
                                fontSize="md"
                                onChangeText={handleChange("cpf")}
                                onBlur={handleBlur("cpf")}
                                value={values.cpf}
                                placeholder="CPF"
                                backgroundColor="gray.200"
                                borderRadius={8}
                                InputLeftElement={
                                    <Icon
                                        as={<MaterialIcons name="person" />}
                                        size={5}
                                        ml={2}
                                        color="gray.500"
                                    />
                                }
                            />
                        </FormControl>
                        <FormControl isInvalid={Boolean(errors.password && touched.password)}>
                            <Stack>
                                <Input
                                    fontSize="md"
                                    p={"3"}
                                    onChangeText={handleChange("password")}
                                    onBlur={handleBlur("password")}
                                    value={values.password}
                                    placeholder="Senha"
                                    type={showPassword ? "password" : "text"}
                                    backgroundColor="gray.200"
                                    borderRadius={8}
                                    InputLeftElement={
                                        <Icon
                                            as={<MaterialIcons name="lock" />}
                                            size={5}
                                            ml={2}
                                            color="gray.500"
                                        />
                                    }
                                    InputRightElement={
                                        <IconButton
                                            icon={
                                                <Icon
                                                    as={
                                                        <MaterialIcons
                                                            name={showPassword ? "visibility" : "visibility-off"}
                                                            onPress={changePasswordVisibility}
                                                        />
                                                    }
                                                    color="gray.500"
                                                    size={5}
                                                    ml={2}
                                                />
                                            }
                                        />
                                    }
                                />
                            </Stack>
                        </FormControl>
                        <Link alignSelf="flex-end" onPress={() => navigation.navigate("recover-password")}>
                            Recuperar Senha
                        </Link>
                    </Stack>

                    <Button
                        p={"3.5"}
                        style={{ backgroundColor: "#37BD3B" }}
                        fontWeight="bold"
                        // fontSize="2xl"
                        borderRadius={8}
                        width="40"
                        alignSelf={"center"}
                        onPress={() => handleSubmit()}
                    >
                        {
                            isLoading ?
                                <ActivityIndicator color="white" animating={true} size="small" /> :
                                <Text
                                    fontWeight="bold"
                                    style={{ fontSize: 16 }}
                                    color="white"
                                >
                                    Entrar
                                </Text>
                        }
                    </Button>
                </Stack>
            )}
        </Formik>
    )
}