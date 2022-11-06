import { Box, Center, Image, KeyboardAvoidingView, Text, VStack } from "native-base";
import React from "react";
import { StyleSheet } from "react-native";

import { SignForm } from "./form";

const LogoImage = require("../../../assets/logo.png")

export const SiginInScreen = () => {
    return (
        <KeyboardAvoidingView h={{ lg: "auto" }} behavior="height">
            <Center height="full">
                <VStack
                    space={"12"}
                    flex={1}
                    h="full"
                    w="full"
                    p={10}
                    alignItems="center"
                    justifyContent="center"
                >
                    <Box alignItems="center">
                        <Image
                            source={LogoImage}
                            alt="Logo"
                        />
                        <Text fontSize="2xl" fontWeight="bold">Bem Vindo</Text>
                        <Text>Informe seu CPF e senha</Text>
                    </Box>
                    <SignForm />
                </VStack>
            </Center>
        </KeyboardAvoidingView>
    )
}