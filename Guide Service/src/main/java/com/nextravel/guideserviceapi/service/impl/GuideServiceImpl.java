package com.nextravel.guideserviceapi.service.impl;


import com.google.gson.Gson;
import com.nextravel.guideserviceapi.dto.GuideDTO;
import com.nextravel.guideserviceapi.entity.Guide;
import com.nextravel.guideserviceapi.exception.SaveFailException;
import com.nextravel.guideserviceapi.repo.GuideRepo;
import com.nextravel.guideserviceapi.service.GuidService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional
public class GuideServiceImpl implements GuidService {

    ModelMapper mapper;
    Gson gson;
    GuideRepo guideRepo;
    public GuideServiceImpl(ModelMapper mapper, Gson gson, GuideRepo guideRepo) {
        this.mapper = mapper;
        this.gson = gson;
        this.guideRepo = guideRepo;
    }
    @Override
    public int saveGuide(GuideDTO guideDTO) throws SaveFailException {
        try {
            Guide map = mapper.map(guideDTO, Guide.class);
            exportImages(guideDTO, map);
            return guideRepo.save(map).getId();
        }catch (Exception e){
            throw new SaveFailException("Operation Fail", e);
        }

    }

    @Override
    public void updateGuide(GuideDTO guideDTO) {

    }

    @Override
    public void deleteGuide(int id) {

    }

    @Override
    public GuideDTO getGuide(int id) {
        return null;
    }

    public void exportImages(GuideDTO guideDTO, Guide guide) {
        String dt = LocalDate.now().toString().replace("-", "_") + "__"
                + LocalTime.now().toString().replace(":", "_");
        try {
            InputStream is = new ByteArrayInputStream(guideDTO.getGuideIdFront());
            BufferedImage bi = ImageIO.read(is);
            File outputfile = new File("images/guide/front/" + dt + "_" + ".jpg");
            ImageIO.write(bi, "jpg", outputfile);
            guide.setGuideIdFront(outputfile.getAbsolutePath());

            is = new ByteArrayInputStream(guideDTO.getGuideIdRear());
            bi = ImageIO.read(is);
            outputfile = new File("images/guide/rear/" + dt + "_" + ".jpg");
            ImageIO.write(bi, "jpg", outputfile);
            guide.setGuideIdRear(outputfile.getAbsolutePath());

            is = new ByteArrayInputStream(guideDTO.getNicFront());
            bi = ImageIO.read(is);
            outputfile = new File("images/guide/nic/front/" + dt + "_" + ".jpg");
            ImageIO.write(bi, "jpg", outputfile);
            guide.setNicFront(outputfile.getAbsolutePath());

            is = new ByteArrayInputStream(guideDTO.getNicRear());
            bi = ImageIO.read(is);
            outputfile = new File("images/guide/nic/rear/" + dt + "_" + ".jpg");
            ImageIO.write(bi, "jpg", outputfile);
            guide.setNicRear(outputfile.getAbsolutePath());

            is = new ByteArrayInputStream(guideDTO.getProfilePic());
            bi = ImageIO.read(is);
            outputfile = new File("images/guide/profile/" + dt + "_" + ".jpg");
            ImageIO.write(bi, "jpg", outputfile);
            guide.setProfilePic(outputfile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importImages(GuideDTO guideDTO, Guide guide) {
        try {
            BufferedImage r = ImageIO.read(new File(guide.getGuideIdFront()));
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ImageIO.write(r, "jpg", b);
            byte[] imgData= b.toByteArray();
            guideDTO.setGuideIdFront(imgData);

            r = ImageIO.read(new File(guide.getGuideIdRear()));
            b = new ByteArrayOutputStream();
            ImageIO.write(r, "jpg", b);
            imgData= b.toByteArray();
            guideDTO.setGuideIdRear(imgData);

            r = ImageIO.read(new File(guide.getNicFront()));
            b = new ByteArrayOutputStream();
            ImageIO.write(r, "jpg", b);
            imgData= b.toByteArray();
            guideDTO.setNicFront(imgData);

            r = ImageIO.read(new File(guide.getNicRear()));
            b = new ByteArrayOutputStream();
            ImageIO.write(r, "jpg", b);
            imgData= b.toByteArray();
            guideDTO.setNicRear(imgData);

            r = ImageIO.read(new File(guide.getProfilePic()));
            b = new ByteArrayOutputStream();
            ImageIO.write(r, "jpg", b);
            imgData= b.toByteArray();
            guideDTO.setProfilePic(imgData);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteImages(Guide guide) {
        File file = new File(guide.getGuideIdFront());
        boolean delete = file.delete();
        System.out.println("Images " + delete);

        file = new File(guide.getGuideIdRear());
        delete = file.delete();
        System.out.println("Images " + delete);

        file = new File(guide.getNicFront());
        delete = file.delete();
        System.out.println("Images " + delete);

        file = new File(guide.getNicRear());
        delete = file.delete();
        System.out.println("Images " + delete);

        file = new File(guide.getProfilePic());
        delete = file.delete();
        System.out.println("Images " + delete);

    }
}
