package spring.project.review.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.project.common.model.Criteria;
import spring.project.common.model.PageMaker;
import spring.project.common.model.SearchCriteria;
import spring.project.member.dto.UserVO;
import spring.project.movie.dto.MovieVO;
import spring.project.review.dto.ReviewDto;
import spring.project.review.service.ReviewService;

@Controller
@RequestMapping("/review/")
public class ReviewController {
    
	// controller -> service 이동
			@Inject 
			private ReviewService reviewService;
			
			// reviewList(search+page)
			@RequestMapping("/list")
			public String SearchListPage(SearchCriteria cri, Model model, String sort)
			throws Exception  {
				if (sort==null) {
					sort = "reg_date";
				}
			System.out.println("sort : "+  sort);
			System.out.println("ReviewController.SearchListPage입니다.");
			model.addAttribute("list", reviewService.list(cri,sort));
			PageMaker pageMaker = new PageMaker();
			// pageMaker 입력해야할 순서 cri -> totalCount(DAO)
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(reviewService.getRow(cri));
			System.out.println(pageMaker);
			model.addAttribute("pageMaker", pageMaker);
			// /WEB-INF/views/ + review/list + .jsp
			return "review/list";
			}
		
			// 리뷰 글 보기
			@RequestMapping("/view")
			public String view(Model model, int no,	
					@ModelAttribute("cri") Criteria cri) //받아오는 데이터를 지정
					throws Exception {
				try {
					System.out.println("ReviewController.view().no:"+no);
					// read(읽어올 글번호, 1증가여부)
					model.addAttribute("view", reviewService.view(no,true));
					// /WEB-INF/views/ + board/read + .jsp
					return "review/view";
				} catch (Exception e) {
					throw new Exception("리뷰 목록 보기 중 오류가 발생하였습니다.");
				}
			}

			// 리뷰 글 등록 
			@RequestMapping(value="/write", method= RequestMethod.GET)
			public String write(Model model, MovieVO movieVO) throws Exception {
				System.out.println("ReviewController.write입니다.");
				model.addAttribute("getMov_no", reviewService.getMov_no(movieVO));
				return "review/write";
			}

			// 리뷰 글 등록 처리
			@RequestMapping(value="/write", method= RequestMethod.POST)
			public String writeProcess(@ModelAttribute("reviewDto") ReviewDto reviewDto, 
			RedirectAttributes rttr, HttpSession session, UserVO userVO) 
					throws Exception{
				System.out.println(reviewDto);
				//세션 id
				 System.out.println("나오냐"+((UserVO)session.getAttribute("login")));
				String id = ((UserVO)session.getAttribute("login")).getId();
				reviewDto.setId(id);
				System.out.println("아이디냐/"+ id);
				reviewService.write(reviewDto);
				//등록 완료되면 완료 메시지 띄우기
				rttr.addFlashAttribute("msg", "WRITESUCCESS");
				return "redirect:list";
			}
			
//			//사진 이미지 재조정해서 저장
//			private String uploadFileAndResize(MultipartFile multipartFile) throws Exception{
//				// 첨부 파일의 정보 출력
//				System.out.println("파일사이즈:"+multipartFile.getSize());
//				System.out.println("파일의 MIME Type:"+multipartFile.getContentType());
//
//				// 폴더 위치에 저장 - 이미지 파일인 경우는 사이즈 조정한다. ::
//				// 사진 리스트용 작은 이미지 - 높이로 80px 로 맞춘다.
//				String fileName = 
//				UploadFileUtils.uploadFile(uploadPath, multipartFile,
//					true, 80, "s_");
//				// view 큰 이미지 - 너비를 800px로 맞춘다.
//				UploadFileUtils.makeThumbnail(uploadPath,
//						fileName.substring(fileName.indexOf("_")+1),
//						false, 600, "b_");
//				return fileName;
//			}
//			
			
			// 리뷰 글 수정 - GET
			@RequestMapping(value="/update", method=RequestMethod.GET)
			public String update(int no,Model model, @ModelAttribute("cri") Criteria cri, MovieVO movieVO) 
					throws Exception{
				System.out.println("ReviewController.update"); 
				model.addAttribute("getMov_no", reviewService.getMov_no(movieVO));
				model.addAttribute("view", reviewService.view(no, false));
				return "review/update";
			}
			// 리뷰 글 수정 처리 - POST
			@RequestMapping(value="/update", method=RequestMethod.POST)
			public String updateProcess(ReviewDto reviewDto, RedirectAttributes rttr, Criteria cri)
			 throws Exception{
				System.out.println("ReviewController.updateProcess.reviewDto:"+reviewDto); 
				reviewService.update(reviewDto);
				rttr.addFlashAttribute("msg","UPDATESUCCESS");
				return "redirect:view?no="+reviewDto.getReview_no()
						+"&page="+cri.getPage()+"&perpageNum="+cri.getPerPageNum();
			}
			// 리뷰 글 삭제 처리
			@RequestMapping("/delete")
			public String deleteProcess(int no, RedirectAttributes rttr) 
			throws Exception{
				System.out.println("ReviewController.delete.no:"+no);
				reviewService.delete(no);
				rttr.addFlashAttribute("msg","DELETESUCCESS");
				return "redirect:list";
			}
	}
