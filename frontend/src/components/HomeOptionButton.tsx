import React from "react";
import { Button, HStack, Text } from "native-base";
import { MaterialIcons } from "@expo/vector-icons"

export interface HomeOptionProps {
    icon: any
    text: string
    onPress: Function
}

export const HomeOptionButton = ({ icon, text, onPress }: HomeOptionProps) => {
    return (
        <Button
            h="24"
            w="full"
            bg="white"
            _pressed={{ backgroundColor: "gray.200" }}
            onPress={() => onPress()}
        >
            <HStack justifyContent="center" w="sm">
                <HStack space="3" alignItems="center" alignSelf="flex-start" w="4/5">
                    {icon}
                    <Text fontSize="md" fontWeight="bold">
                        {text}
                    </Text>
                </HStack>
                <MaterialIcons size={30} name="chevron-right" />
            </HStack>
        </Button>
    )
}