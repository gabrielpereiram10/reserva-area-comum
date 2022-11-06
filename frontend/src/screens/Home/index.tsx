import { Button, Center, Text } from "native-base";
import React from "react";
import { useAuth } from "../../contexts/auth";

export const HomeScreen = () => {
    const { signOut } = useAuth()
    return (
        <Center h="full" w="full">
            <Button
                size="md"
                colorScheme="red"
                onPress={signOut}
            >
                Sair
            </Button>
        </Center>
    )
}