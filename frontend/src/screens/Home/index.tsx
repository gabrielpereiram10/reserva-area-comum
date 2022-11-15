import { Actionsheet, Box, Button, Divider, HStack, Icon, IconButton, Image, ScrollView, Text, useDisclose } from "native-base";
import React, { useState } from "react";
import { MaterialCommunityIcons } from "@expo/vector-icons"
import * as ImagePicker from 'expo-image-picker';
import { useAuth } from "../../contexts/auth";
import HomeScreenOptions from "./options";


export const HomeScreen = () => {
    const { authData } = useAuth()
    const { isOpen, onOpen, onClose } = useDisclose();
    const [userImage, setUserImage] = useState<string>("")

    const pickImageFromCamera = async () => {
        const result = await ImagePicker.launchCameraAsync({
            mediaTypes: ImagePicker.MediaTypeOptions.Images,
        })

        if (!result.cancelled) {
            setUserImage(result?.uri);
            onClose()
        }
    }

    const pickImageFromGalery = async () => {
        const result = await ImagePicker.launchImageLibraryAsync({
            mediaTypes: ImagePicker.MediaTypeOptions.Images,
            allowsEditing: true,
            aspect: [4, 3],
            quality: 1,
        })

        if (!result.cancelled) {
            setUserImage(result?.uri);
            onClose()
        }
    }

    const removeImage = () => {
        setUserImage("")
        onClose()
    }

    return (
        <>
            <ScrollView w="full" h="full">
                <Box h="sm" p={5} w="full" flex={1} justifyContent="center">
                    <HStack justifyContent="flex-start" alignItems="center" space="4" h="1/2">
                        {
                            userImage ?
                                <Button p="0" borderRadius="full" onPress={onOpen}>
                                    <Image size={100} borderRadius="full" alt="Imagem usuário" source={{ uri: userImage }} />
                                </Button> :
                                <IconButton
                                    p="5"
                                    bg="#37BD3B"
                                    borderRadius={"full"}
                                    icon={<Icon color="white" as={MaterialCommunityIcons} name="image-edit-outline" size="xl" />}
                                    onPress={onOpen}
                                />
                        }
                        <Text fontWeight="bold" fontSize="lg">
                            {authData?.user.name}
                        </Text>
                    </HStack>
                    <Text fontWeight="bold" fontSize="md">Tipo Usuário</Text>
                </Box>
                <Divider />
                <HomeScreenOptions />
            </ScrollView>
            <Actionsheet isOpen={isOpen} onClose={onClose} size="full">
                <Actionsheet.Content>
                    <Actionsheet.Item
                        _pressed={{ backgroundColor: "gray.200" }}
                        startIcon={<MaterialCommunityIcons name="camera-enhance-outline" size={25} />}
                        onPress={pickImageFromCamera}
                    >
                        Câmera
                    </Actionsheet.Item>
                    <Actionsheet.Item
                        _pressed={{ backgroundColor: "gray.200" }}
                        startIcon={<MaterialCommunityIcons name="image-edit-outline" size={25} />}
                        onPress={pickImageFromGalery}
                    >
                        Galeria
                    </Actionsheet.Item>
                    {
                        isOpen && userImage && (
                            <Actionsheet.Item
                                _pressed={{ backgroundColor: "gray.200" }}
                                startIcon={<MaterialCommunityIcons name="trash-can-outline" size={25} />}
                                onPress={removeImage}
                            >
                                Remover imagem
                            </Actionsheet.Item>
                        )
                    }
                </Actionsheet.Content>
            </Actionsheet>
        </>
    )
}