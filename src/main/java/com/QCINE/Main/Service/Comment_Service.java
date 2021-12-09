package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.Comment_DTO;
import com.QCINE.Main.DTO.ResultPageComment;
import com.QCINE.Main.Entity.*;
import com.QCINE.Main.Entity.Embedded.CommentId;
import com.QCINE.Main.Repository.Comment_Repository;
import com.QCINE.Main.Repository.Hoadon_Repository;
import com.QCINE.Main.Repository.Phim_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Comment_Service {
    @Autowired
    Comment_Repository comment_repository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    Customer_Service customer_service;

    @Autowired
    Phim_Repository phim_repository;

    @Autowired
    Hoadon_Repository hoadon_repository;


    public ResultPageComment getListCommentPagination(int page, int size, int idphim){
        ResultPageComment resultPage = new ResultPageComment();
        try {
            Sort sort = new Sort(Sort.Direction.DESC, "dateCreated");
            Pageable pageable = new PageRequest(page - 1,size,sort);
            Phim_Entity phim_entity = phim_repository.findByIdPhim(idphim);

            Page<Comment_Entity> Result = comment_repository.findByPhim(phim_entity, pageable);

            // parse list
            List<Comment_DTO> listDTO = Result.getContent().stream().map(
                    comment_entity -> {
                        Comment_DTO  comment_dto = new Comment_DTO();
                        comment_dto.setEmail(comment_entity.getCustomer().getUserEntity().getEmail());
                        comment_dto.setIdCustomer(comment_entity.getCustomer().getIdCustomer());
                        comment_dto.setCmt(comment_entity.getCmt());
                        comment_dto.setRating(comment_entity.getRating());
                        comment_dto.setIdPhim(comment_entity.getPhim().getIdPhim());
                        comment_dto.setDateCreated(comment_entity.getDateCreated());
                        return  comment_dto;
                    }).collect(Collectors.toList());

            resultPage.setPage(Result.getNumber()  + 1 );
            resultPage.setListResult(listDTO);
            resultPage.setTotalpage(Result.getTotalPages());
        }catch (Exception e){
            System.out.println("Exception: "+e);
        }
        return  resultPage;
    }


    @Transactional
    public Comment_DTO  createComment(Comment_DTO comment, String username){
        Comment_Entity comment_entity = mapper.map(comment,Comment_Entity.class);
        try {
            Customer_Entity customer_entity = customer_service.findCustomerEntityByUsername(username);
            Phim_Entity phim_entity = phim_repository.findByIdPhim(comment.getIdPhim());
            CommentId commentId = new CommentId();
            commentId.setIdCustomer(customer_entity.getIdCustomer());
            commentId.setIdPhim(phim_entity.getIdPhim());
            comment_entity.setId(commentId);

            comment_entity.setRating(comment.getRating());
            comment_entity.setCmt(comment.getCmt());
            comment_entity.setDateCreated(comment.getDateCreated());
            comment_entity.setCustomer(customer_entity);
            comment_entity.setPhim(phim_entity);
            comment_repository.save(comment_entity);
            comment.setIdCustomer(customer_entity.getIdCustomer());
            comment.setEmail(customer_entity.getUserEntity().getEmail());
        }catch (Exception e){
            System.out.println("Exception: "+e);
        }
        return comment;
    }


    public boolean checkComment(int idphim ,String username) throws ParseException {
        Customer_Entity customer_entity = customer_service.findCustomerEntityByUsername(username);
        Phim_Entity phim_entity = phim_repository.findByIdPhim(idphim);

        if(ObjectUtils.isEmpty(customer_entity) || ObjectUtils.isEmpty(phim_entity)){
            return false;
        }

        List<HoaDon_Entity> list = hoadon_repository.findByCustomerEntity(customer_entity);
        Lich_Entity lichT = new Lich_Entity();
        boolean kt = false;
        for(HoaDon_Entity hoadon : list){
            if(hoadon.getLichEntity().getPhimEntity().getIdPhim() == idphim){
                kt = true;
                lichT = hoadon.getLichEntity();
                break;
            }
        }

        if(!kt){
            return false;
        }

        // check time comment
        Date timeN = new Date();
        String strDate = lichT.getNgay().toString();
        String strTime = lichT.getGio().toString();
        Date TimeC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate +" "+strTime);
        if (( timeN.getTime() - TimeC.getTime() < 0)){
            return  false;
        }

        Comment_Entity comment_entity = comment_repository.findByPhimAndCustomer(phim_entity,customer_entity);
        if(ObjectUtils.isEmpty(comment_entity)){
            return false;
        }
        return true;
    }

}
