package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.thongke.OutputKVThongKe_DTO;
import com.QCINE.Main.DTO.thongke.OutputThongKe_DTO;
import com.QCINE.Main.Entity.*;
import com.QCINE.Main.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class Thongke_Service {

    @Autowired
    Phim_Repository phim_repository;

    @Autowired
    Hoadon_Repository hoadon_repository;

    @Autowired
    KhuVuc_Repository khuVuc_repository;

    @Autowired
    Rap_Repository rap_repository;

    public List<OutputThongKe_DTO> ThongkeRapByDate(String mode, String idKhuvuc ,String idRap, int idPhim, String strDateFrom,
                                              String strDateTo) {
        System.out.println("vào "+mode);
        List<OutputThongKe_DTO> result = new ArrayList<>();

        if(mode.toUpperCase().equals("KHUVUC")){

            KhuVuc_Entity khuVuc = khuVuc_repository.findByIdKhuVuc(idKhuvuc);
            List<Rap_Entity> listRap = rap_repository.findAllByKhuvuc(khuVuc);
            if(ObjectUtils.isEmpty(khuVuc) || listRap.size() == 0){
                return  result;
            }

            for (Rap_Entity rap : listRap){
                // id rap

                List<HoaDon_Entity> listHDRap = hoadon_repository.findByIdRapAndDate(rap.getIdRap(),strDateFrom,strDateTo);

                if (listHDRap.size() == 0 ){
                    continue;
                }

                OutputThongKe_DTO output1 = new OutputThongKe_DTO();
                float totalR = 0;
                int pointR = 0;
                int slveR = 0;
                for (HoaDon_Entity hoaDon : listHDRap){
                    totalR+= hoaDon.getTotal();
                    pointR+= hoaDon.getUsedPoint();
                    slveR+= hoaDon.getCtHoaDon_Entity().size();
                }

                output1.setName(rap.getTenRap());
                output1.setDoanhThu(totalR);
                output1.setPointUsed(pointR);
                output1.setSoVe(slveR);
                result.add(output1);
            }

            return result;
        }else if(mode.toUpperCase().equals("RAP") && (idKhuvuc.equals("") == false)){ // thống kê rap so khu vuc
            Rap_Entity rap = rap_repository.findByIdRap(idRap);
            if( ObjectUtils.isEmpty(rap) ){
                return result;
            }

            List<HoaDon_Entity> listHDRap = hoadon_repository.findByIdRapAndDate(idRap,strDateFrom,strDateTo);
            OutputThongKe_DTO output1 = new OutputThongKe_DTO();
            float totalR = 0;
            int pointR = 0;
            int slveR = 0;
            for (HoaDon_Entity hoaDon : listHDRap){
                totalR+= hoaDon.getTotal();
                pointR+= hoaDon.getUsedPoint();
                slveR+= hoaDon.getCtHoaDon_Entity().size();
            }

            output1.setName(rap.getTenRap());
            output1.setDoanhThu(totalR);
            output1.setPointUsed(pointR);
            output1.setSoVe(slveR);
            result.add(output1);

            // id rap
            List<HoaDon_Entity> listHDKhuvuc = hoadon_repository.findByIdKhuvucAndDate(idKhuvuc,strDateFrom,strDateTo);
            System.out.println(" size "+listHDKhuvuc.size());
            OutputThongKe_DTO output2 = new OutputThongKe_DTO();
            float totalK = 0;
            int pointK = 0;
            int slveK = 0;
            for (HoaDon_Entity hoaDon : listHDKhuvuc){
                totalK+= hoaDon.getTotal();
                pointK+= hoaDon.getUsedPoint();
                slveK+= hoaDon.getCtHoaDon_Entity().size();
            }

            output2.setName(rap.getTenRap());
            output2.setDoanhThu(totalK);
            output2.setPointUsed(pointK);
            output2.setSoVe(slveK);
            result.add(output2);

            return result;
        }else if(mode.toUpperCase().equals("RAP") && idKhuvuc.equals("")){ // thống kê rap

            List<Phim_Entity> listPhim = phim_repository.findByIdRapAndDate(idRap,strDateFrom, strDateTo);

            if(listPhim.size() == 0  ){
                return result;
            }

            for(Phim_Entity phim : listPhim){

                List<HoaDon_Entity> listHDPhim = hoadon_repository.findByIdRapAndPhimAndDate(idRap,phim.getIdPhim(),strDateFrom,strDateTo);
                if(listHDPhim.size() == 0 ){
                    return result;
                }

                // id phim
                OutputThongKe_DTO output1 = new OutputThongKe_DTO();
                float total = 0;
                int point = 0;
                int slve = 0;
                for (HoaDon_Entity hoaDon : listHDPhim){
                    total+= hoaDon.getTotal();
                    point+= hoaDon.getUsedPoint();
                    slve+= hoaDon.getCtHoaDon_Entity().size();
                }
                output1.setName(phim.getTenPhim());
                output1.setDoanhThu(total);
                output1.setPointUsed(point);
                output1.setSoVe(slve);
                result.add(output1);
            }

            return result;
        }else{ // thống kê phim
            if(idPhim <= 0){
                return result;
            }

            Phim_Entity phim = phim_repository.findByIdPhim(idPhim);
            Rap_Entity rap = rap_repository.findByIdRap(idRap);
            if(ObjectUtils.isEmpty(phim) || ObjectUtils.isEmpty(rap) ){
                return result;
            }

            List<HoaDon_Entity> listHDRap = hoadon_repository.findByIdRapAndDate(idRap,strDateFrom,strDateTo);
            List<HoaDon_Entity> listHDPhim = hoadon_repository.findByIdRapAndPhimAndDate(idRap,idPhim,strDateFrom,strDateTo);
            if(listHDRap.size() == 0 && listHDPhim.size() == 0 ){
                return result;
            }

            // id phim
            OutputThongKe_DTO output1 = new OutputThongKe_DTO();
            float total = 0;
            int point = 0;
            int slve = 0;
            for (HoaDon_Entity hoaDon : listHDPhim){
                total+= hoaDon.getTotal();
                point+= hoaDon.getUsedPoint();
                slve+= hoaDon.getCtHoaDon_Entity().size();
            }
            output1.setName(phim.getTenPhim());
            output1.setDoanhThu(total);
            output1.setPointUsed(point);
            output1.setSoVe(slve);
            result.add(output1);

            // id rap
            OutputThongKe_DTO output2 = new OutputThongKe_DTO();
            float totalR = 0;
            int pointR = 0;
            int slveR = 0;
            for (HoaDon_Entity hoaDon : listHDRap){
                totalR+= hoaDon.getTotal();
                pointR+= hoaDon.getUsedPoint();
                slveR+= hoaDon.getCtHoaDon_Entity().size();
            }

            output2.setName(rap.getTenRap());
            output2.setDoanhThu(totalR);
            output2.setPointUsed(pointR);
            output2.setSoVe(slveR);
            result.add(output2);

            return result;
        }
    }


    public Object ThongkeKVByDate(String mode, String idKhuvuc, String idRap, String year) {

        Integer[] mang = {31,28,31,30,31,30,31,31,30,31,30,31};

        if(mode.toUpperCase().equals("RAP")){ // biến động doanh thu rap
            List<OutputThongKe_DTO> result = new ArrayList<>();

            Rap_Entity rap = rap_repository.findByIdRap(idRap);
            if(ObjectUtils.isEmpty(rap) ){
                return result;
            }


            for (int i = 1 ; i < 13; i ++){

                String strDateFrom = year+"-"+i+"-"+"1";
                String strDateTo = year+"-"+i+"-"+mang[i-1];

                List<HoaDon_Entity> listHDRap = hoadon_repository.findByIdRapAndDate(idRap,strDateFrom,strDateTo);
//                if(listHDRap.size() == 0) continue;

                OutputThongKe_DTO output2 = new OutputThongKe_DTO();
                float totalR = 0;
                int pointR = 0;
                int slveR = 0;
                for (HoaDon_Entity hoaDon : listHDRap){
                    totalR+= hoaDon.getTotal();
                    pointR+= hoaDon.getUsedPoint();
                    slveR+= hoaDon.getCtHoaDon_Entity().size();
                }

                output2.setName("tháng "+i);
                output2.setDoanhThu(totalR);
                output2.setPointUsed(pointR);
                output2.setSoVe(slveR);
                result.add(output2);
            }

            return  result;
        }else{// biến động doanh thu khu vuc
            List<OutputKVThongKe_DTO> result = new ArrayList<>();

            KhuVuc_Entity khuVuc = khuVuc_repository.findByIdKhuVuc(idKhuvuc);
            List<Rap_Entity> listRap = rap_repository.findAllByKhuvuc(khuVuc);
            if(ObjectUtils.isEmpty(khuVuc) || listRap.size() == 0){
                return  result;
            }

            for(Rap_Entity rap : listRap){
                OutputKVThongKe_DTO output = new OutputKVThongKe_DTO();
                List<OutputThongKe_DTO> listTemp = new ArrayList<>();
                for (int i = 1 ; i < 13; i ++){

                    String strDateFrom = year+"-"+i+"-"+"1";
                    String strDateTo = year+"-"+i+"-"+mang[i-1];

                    List<HoaDon_Entity> listHDRap = hoadon_repository.findByIdRapAndDate(rap.getIdRap(),strDateFrom,strDateTo);
//                    if(listHDRap.size() == 0) continue;

                    OutputThongKe_DTO output2 = new OutputThongKe_DTO();

                    float totalR = 0;
                    int pointR = 0;
                    int slveR = 0;
                    for (HoaDon_Entity hoaDon : listHDRap){
                        totalR+= hoaDon.getTotal();
                        pointR+= hoaDon.getUsedPoint();
                        slveR+= hoaDon.getCtHoaDon_Entity().size();
                    }

                    output2.setName("tháng "+i);
                    output2.setDoanhThu(totalR);
                    output2.setPointUsed(pointR);
                    output2.setSoVe(slveR);
                    listTemp.add(output2);
                }

                output.setTenRap(rap.getTenRap());
                output.setList(listTemp);
                result.add(output);
            }

            return  result;
        }
    }
}
