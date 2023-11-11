package com.fastfood.fastfood.controller;

import com.fastfood.fastfood.entity.Plato;
import com.fastfood.fastfood.service.impl.FileUploadServiceImpl;
import com.fastfood.fastfood.service.impl.PlatoService;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/plato")
public class PlatoController {

    @Autowired
    private PlatoService serPlato;

    @Autowired
    private FileUploadServiceImpl serFile;

    @GetMapping
    @RequestMapping("/lista")
    public List<Plato> listarTodo(){
      return  serPlato.listAll();
    }

    @GetMapping("/photos/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
        byte[] image = new byte[0];
        try {
            image = FileUtils.readFileToByteArray(new File("photos/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    @PostMapping
    @RequestMapping("/save")
    public Plato save(@RequestParam("plato") String strPlato, @RequestParam("fichero") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        //Establecemos el directorio donde se subiran nuestros ficheros
        String uploadDir = "photos";

        Gson gson = new Gson();
        Plato p = new Plato();
        p=gson.fromJson(strPlato, Plato.class);

        serFile.saveFile(uploadDir, fileName, multipartFile);
        p.setImagen("/"+uploadDir+"/"+fileName);
        return serPlato.registrar(p);

    }

    @PutMapping
    @RequestMapping("/update")
    public Plato update(@RequestParam("plato") String strPlato, @RequestParam("fichero") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        //Establecemos el directorio donde se subiran nuestros ficheros
        String uploadDir = "photos";

        Gson gson = new Gson();
        Plato p = new Plato();
        p=gson.fromJson(strPlato, Plato.class);

        serFile.updateFile(uploadDir, fileName, multipartFile);
        return serPlato.modificar(p);

    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        serPlato.eliminar(id);
    }

    @GetMapping
    @RequestMapping("/search/{id}")
    public Plato search(@PathVariable Integer id){
        return serPlato.searchById(id);
    }

}
