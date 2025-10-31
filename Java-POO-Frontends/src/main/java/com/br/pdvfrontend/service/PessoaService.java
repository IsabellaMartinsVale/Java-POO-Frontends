package com.br.pdvfrontend.service;

import com.br.pdvfrontend.model.Pessoa;
import com.br.pdvfrontend.util.HttpClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PessoaService {

    private final Gson gson = new Gson();

    public List<Pessoa> listar() {
        try {
            String jsonResponse = HttpClient.sendGetRequest("/pessoas");
            Type listType = new TypeToken<List<Pessoa>>() {}.getType();
            return gson.fromJson(jsonResponse, listType);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Pessoa buscarPorId(Long id) {
        try {
            String jsonResponse = HttpClient.sendGetRequest("/pessoas/" + id);
            return gson.fromJson(jsonResponse, Pessoa.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Pessoa salvar(Pessoa pessoa) {
        try {
            String jsonPayload = gson.toJson(pessoa);
            String jsonResponse = HttpClient.sendPostRequest("/pessoas", jsonPayload);
            return gson.fromJson(jsonResponse, Pessoa.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deletar(Long id) {
        try {
            HttpClient.sendDeleteRequest("/pessoas/" + id);
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pessoa login(String email, String senha) {
        try {
            Map<String, String> credentials = Map.of("email", email, "senha", senha);
            String jsonPayload = gson.toJson(credentials);
            String jsonResponse = HttpClient.sendPostRequest("/auth/login", jsonPayload);

            if (jsonResponse == null || jsonResponse.isEmpty()) {
                return null;
            }
            return gson.fromJson(jsonResponse, Pessoa.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}