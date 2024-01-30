package service;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PostDao;
import model.post;
import model.BoardWrite; // 수정된 모델 클래스


@Service
public class PostService {
    
    @Autowired
    private PostDao postDao; // PostDao를 주입합니다.

    public List<post> getPostList() {
    	System.out.println("포스트 서비스 넘어옴");
        return postDao.getAllPosts(); // PostDao를 사용하여 데이터베이스에서 게시글 목록을 가져옵니다.
    }
    
    //write poast 함수 
    public void writePost(String memName, String memId, String boardSubject, String boardContent) {
    	System.out.println("포스트 글쓰기 write poast 넘어오는거 체크 해보기");
        // 게시글 작성 로직
        BoardWrite newPost = new BoardWrite();
        newPost.setMem_name(memName);
        newPost.setMem_id(memId);
        newPost.setBoard_subject(boardSubject);
        newPost.setBoard_content(boardContent);
        newPost.setReg_date(new Date());
        
        // PostDao를 사용하여 게시글을 데이터베이스에 저장
        postDao.savePost(newPost); // "savePost"는 PostDao에 추가해야 하는 메서드 이름입니다.
    }

	public post getPostBySeq(int seq) {
		// TODO Auto-generated method stub
		 return postDao.getPostBySeq(seq);
	}

	 // 업데이트 함수
    public void updatePost(Integer seq, String memName, String memId, String boardSubject, String boardContent) {
        // seq로 해당 글을 가져옴
        post existingPost = postDao.getPostBySeq(seq);

        // 가져온 글이 존재하면 업데이트 수행
        if (existingPost != null) {
            existingPost.setMem_name(memName);
            existingPost.setMem_id(memId);
            existingPost.setBoard_subject(boardSubject);
            existingPost.setBoard_content(boardContent);

            // 수정일 업데이트
            existingPost.setUpt_date(new Date());

            // PostDao를 사용하여 데이터베이스에 업데이트
            postDao.updatePost(existingPost); // "updatePost"는 PostDao에 추가해야 하는 메서드 이름입니다.
        } else {
            // 가져온 글이 없으면 예외 처리 또는 로깅 등을 수행
            System.out.println("해당 글이 존재하지 않습니다. 글 번호: " + seq);
            // 예외 처리 또는 로깅 등을 추가할 수 있습니다.
        }
    }
    
    

   
    
    
    
    
}
