import React from "react";
import { VStack } from "native-base";
import { MaterialCommunityIcons } from "@expo/vector-icons"

import { HomeOptionButton, HomeOptionProps } from "../../components/HomeOptionButton";
import { useAuth } from "../../contexts/auth";

export default function HomeScreenOptions() {
    const { signOut } = useAuth()

    const options: HomeOptionProps[] = [
        {
            icon: <MaterialCommunityIcons size={30} name="hand-extended-outline" />,
            text: "Reservar Espaço",
            onPress: () => { }
        }, {
            icon: <MaterialCommunityIcons size={30} name="file-table-outline" />,
            text: "Solicitações",
            onPress: () => { }
        }, {
            icon: <MaterialCommunityIcons size={30} name="history" />,
            text: "Histórico de Reservas",
            onPress: () => { }
        }, {
            icon: <MaterialCommunityIcons size={30} name="home-edit-outline" />,
            text: "Gerenciar Espaços",
            onPress: () => { }
        }, {
            icon: <MaterialCommunityIcons size={30} name="tools" />,
            text: "Configurações",
            onPress: () => { }
        }, {
            icon: <MaterialCommunityIcons size={30} name="arrow-left-bottom" />,
            text: "Sair",
            onPress: () => signOut()
        }
    ]

    return (
        <VStack w="full" space="0.5">
            {
                options.map((option, index) => (
                    <HomeOptionButton key={index} {...option} />
                ))
            }
        </VStack>
    )
}