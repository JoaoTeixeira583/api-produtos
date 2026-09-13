package com.example.api_produtos.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_produtos.Config.JwtService;
import com.example.api_produtos.Dto.UsuarioResponseDTO;
import com.example.api_produtos.Entity.Usuario;
import com.example.api_produtos.Service.UsuarioService;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    public AuthController(UsuarioService usuarioService,JwtService jwtService){
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public UsuarioResponseDTO criarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo =  usuarioService.registrar(usuario);
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(usuarioSalvo.getId(), usuarioSalvo.getNome(),null);
        return  usuarioResponseDTO;
    }

    @PostMapping("/login")
    // ResponseEntity<Usuario> diz se tiver algo dentro e do usuario
    public ResponseEntity<UsuarioResponseDTO>criarLogin(@RequestBody Usuario usuario){
        Optional<Usuario>resultado = usuarioService.login(usuario);
        if(resultado.isPresent()){
            Usuario usuarioEncontrado = resultado.get();
            String gerarToken = jwtService.gerarToken(usuarioEncontrado.getNome());
            return ResponseEntity.ok(new UsuarioResponseDTO(usuarioEncontrado.getId(), usuarioEncontrado.getNome(), gerarToken));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
    }
    
}
