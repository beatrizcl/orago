package br.unisul.projeto.bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.unisul.projeto.domain.Processo;


public class EvidenciaBack {
	

	Processo domain = new Processo();

	public void upload(FileUploadEvent evento) {

		try {

			UploadedFile x = evento.getFile();
			// Cria um espaço temporario no servidor
			Path t = Files.createTempFile(null, null);
			Files.copy(x.getInputstream(), t, StandardCopyOption.REPLACE_EXISTING);

			domain.setPathTemp(t.toString());

		} catch (Exception e) {
			// TODO: handle exception
		}
		Messages.addGlobalInfo(domain.getPathTemp());

	}

	 public StreamedContent getImg() throws IOException {
			StreamedContent img = null;
	        FacesContext context = FacesContext.getCurrentInstance();

	        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	            return new DefaultStreamedContent();
	        }else {
	            String imageCd = 
	            		context.getExternalContext().
	            		getRequestParameterMap().get("parCd");
	            File f = new File("C://Users/ART3MIS/Documents/evidencias/"+imageCd+".png");
				if(f.isFile()){
					Path path = Paths.get(f.getAbsolutePath());
					InputStream stream = Files.newInputStream(path);
					img = new DefaultStreamedContent(stream);
				}else{
					Path path = Paths.get("C://Users/ART3MIS/Documents/evidencias/branco.png");
					InputStream stream = Files.newInputStream(path);
					img = new DefaultStreamedContent(stream);
				}
	            return img;
	        }
	    }


}
